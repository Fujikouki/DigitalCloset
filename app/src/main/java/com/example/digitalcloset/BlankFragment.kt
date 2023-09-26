package com.example.digitalcloset

import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.digitalcloset.databinding.FragmentBlankBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BlankFragment : Fragment() {

    private val _cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),onActivityResultFromCamera()
    )
    private var _imageUrl:Uri? = null

    private lateinit var _helper: Database
    override fun onAttach(context: Context) {
        super.onAttach(context)
        _helper = Database(requireContext())
    }
    private var _hukuId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_blank, container, false)
        val saveButton = rootView.findViewById<Button>(R.id.savebutton)
        val ImageButton = rootView.findViewById<ImageView>(R.id.image_view)
        val listener = SaveListener()
        val listener1 = CameraListner()
        ImageButton.setOnClickListener(listener1)
        saveButton.setOnClickListener(listener)
        return rootView

    }

    private inner class CameraListner:View.OnClickListener{

        override fun onClick(view: View) {
            val now = Date()
            val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
            val nowStr = dateFormat.format(now)
            Log.d("name",nowStr)
            val fileName = "asd${nowStr}.jpg"
            Log.d("faname",fileName)
            val values = ContentValues()
            values.put(MediaStore.Images.Media.TITLE, fileName)
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            _imageUrl = requireActivity().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
            Log.d("url",_imageUrl.toString())
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,_imageUrl)
            _cameraLauncher.launch(intent)
        }
    }


    private inner class onActivityResultFromCamera:ActivityResultCallback<ActivityResult>{
        override fun onActivityResult(result: ActivityResult) {
            Log.d("カメラ",result.toString())
            if(result.resultCode == RESULT_OK ){

                val ini = view?.findViewById<ImageView>(R.id.image_view)
                ini?.setImageURI(_imageUrl)

            }
        }
    }

    private inner class SaveListener:View.OnClickListener{
        override fun onClick(view: View?) {

//            Log.d("save", view.toString())

            val hukuSpsyu = view?.findViewById<Spinner>(R.id.spinner_huku) ?: requireView().findViewById(R.id.spinner_huku)
            val hukuSpiro = view?.findViewById<Spinner>(R.id.spinner_iro) ?: requireView().findViewById(R.id.spinner_iro)
            val hukuEdit = requireView().findViewById<EditText>(R.id.huku_name)

            val hukusyu:String
            val hukuiro:String
            val hukuname:String

            hukusyu = hukuSpsyu.selectedItem.toString()
            hukuiro = hukuSpiro.selectedItem.toString()
            hukuname = hukuEdit.text.toString()
            Log.d("seikou",hukuiro)
            Log.d("seikou",hukusyu)
            Log.d("seikou",hukuname)

            val bundle = Bundle()

            bundle.putString("hukusyu",hukusyu)
            bundle.putString("hukuiro",hukuiro)
            bundle.putString("hukuname",hukuname)

            fun stringToIntHash(input: String): Int {
                // 文字列をハッシュコードに変換します
                val hashCode = input.hashCode()

                // ハッシュコードをINT型の範囲に収める
                return hashCode and Int.MAX_VALUE
            }

            _hukuId = stringToIntHash(hukuname)

            val db = _helper.writableDatabase

            //主キーが同じ物を削除する
            val sqlDelete = "DELETE FROM clothesmemos WHERE _id = ?"
            var stmt = db.compileStatement(sqlDelete)
            stmt.bindLong(1,_hukuId.toLong())
            stmt.executeUpdateDelete()

            //データベースに値を入力する
            val sqlInsert = "INSERT INTO clothesmemos (_id, clothes_name, clothes_type, clothes_color, clothes_image) VALUES (?, ?, ?, ?, ?)"
            stmt = db.compileStatement(sqlInsert)
            stmt.bindLong(1,_hukuId.toLong())
            stmt.bindString(2,hukuname)
            stmt.bindString(3,hukusyu)
            stmt.bindString(4,hukuiro)
            stmt.bindString(5,_imageUrl.toString())
            stmt.executeInsert()

            //MainFragmentに遷移する
            val transaction = parentFragmentManager.beginTransaction()
            transaction.setReorderingAllowed(true)
            transaction.addToBackStack("Only LIst")
            transaction.replace(R.id.MinFragment,MainFragment::class.java,bundle)
            transaction.commit()

        }
    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }

}

