package com.example.digitalcloset

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class BlankFragment : Fragment() {


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
        val listener = SaveListener()

        saveButton.setOnClickListener(listener)

        return rootView
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
            val sqlInsert = "INSERT INTO clothesmemos (_id, clothes_name, clothes_type, clothes_color) VALUES (?, ?, ?, ?)"
            val stmt = db.compileStatement(sqlInsert)

            stmt.bindLong(1,_hukuId.toLong())
            stmt.bindString(2,hukuname)
            stmt.bindString(3,hukusyu)
            stmt.bindString(4,hukuiro)
            stmt.executeInsert()

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