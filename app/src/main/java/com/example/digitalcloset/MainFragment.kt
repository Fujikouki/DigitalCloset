package com.example.digitalcloset
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {

    private lateinit var _helper: Database
    private lateinit var cursor: Cursor

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _helper = Database(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // 引数の処理が必要な場合はここに記述
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemList = mutableListOf<YourDataModel>()

        // データベースからデータを取得
        try {
            val db = _helper.readableDatabase
            val query = "SELECT * FROM clothesmemos"
            cursor = db.rawQuery(query, null)

            while (cursor.moveToNext()) {
                val idColumnIndex = cursor.getColumnIndex("_id")
                val nameColumnIndex = cursor.getColumnIndex("clothes_name")
                val typeColumnIndex = cursor.getColumnIndex("clothes_type")
                val colorColumnIndex = cursor.getColumnIndex("clothes_color")

                // カラムのインデックスが-1でないことを確認
                if (idColumnIndex >= 0 && nameColumnIndex >= 0) {
                    val id = cursor.getLong(idColumnIndex)
                    val name = cursor.getString(nameColumnIndex)
                    val type = cursor.getString(typeColumnIndex)
                    val color = cursor.getString(colorColumnIndex)

                    // 他の列も同様に取得
                    val dataModel = YourDataModel(id, name, type, color)
                    itemList.add(dataModel)
                }
            }
        } finally {
            cursor.close() // カーソルを閉じる
        }

        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        val bcButton = rootView.findViewById<Button>(R.id.bcButton)
        bcButton.setOnClickListener(bccreta())

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recvi)
        val adapter = RecyclerAdapter(itemList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        return rootView
    }

    private inner class bccreta : View.OnClickListener {
        override fun onClick(view: View) {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // カーソルをクリーンアップ
        cursor?.close()
    }
}
