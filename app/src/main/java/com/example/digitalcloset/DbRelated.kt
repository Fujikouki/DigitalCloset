package com.example.digitalcloset

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext

class DbRelated(context: Context) {
    private lateinit var _helper: Database
    private lateinit var cursor: Cursor


    init {
        // コンストラクタ内で _helper プロパティを初期化
        _helper = Database(context)

    }

// 主キーが同じもの消す処理
    fun Dbdeletion(_hukuId:String):Unit{
    try {
        val db = _helper.writableDatabase
        //主キーが同じ物を削除する
        val sqlDelete = "DELETE FROM clothesmemos WHERE _id = ?"
        val stmt = db.compileStatement(sqlDelete)
        stmt.bindLong(1,_hukuId.toLong())
        Log.d("消去",_hukuId)
        stmt.executeUpdateDelete()
        Log.d("消去",_hukuId)
    }catch (e:Exception){
        Log.d("エラー",e.toString())
    }finally {
        _helper.close()
    }
    }

    fun DbAddition(_hukuId:String,hukuname:String,hukusyu:String,hukuiro:String,_imageUrl:Uri?):Unit{
        try {
            val db = _helper.writableDatabase
            val sqlInsert = "INSERT INTO clothesmemos (_id, clothes_name, clothes_type, clothes_color, clothes_image) VALUES (?, ?, ?, ?, ?)"
            var stmt = db.compileStatement(sqlInsert)
            stmt = db.compileStatement(sqlInsert)
            stmt.bindLong(1,_hukuId.toLong())
            stmt.bindString(2,hukuname)
            stmt.bindString(3,hukusyu)
            stmt.bindString(4,hukuiro)
            stmt.bindString(5,_imageUrl.toString())
            stmt.executeInsert()
        }catch (e:Exception){
            Log.d("エラー",e.toString())
        }finally {
            _helper.close()
        }
    }

    fun DbShow():List<YourDataModel>{

        val itemList = mutableListOf<YourDataModel>()
        try {
            val db = _helper.readableDatabase
            val query = "SELECT * FROM clothesmemos"
            cursor = db.rawQuery(query, null)
            while (cursor.moveToNext()) {
                val idColumnIndex = cursor.getColumnIndex("_id")
                val nameColumnIndex = cursor.getColumnIndex("clothes_name")
                val typeColumnIndex = cursor.getColumnIndex("clothes_type")
                val colorColumnIndex = cursor.getColumnIndex("clothes_color")
                val imageColumnIndex = cursor.getColumnIndex("clothes_image")

                // カラムのインデックスが-1でないことを確認
                if (idColumnIndex >= 0 && nameColumnIndex >= 0) {
                    val id = cursor.getLong(idColumnIndex)
                    val name = cursor.getString(nameColumnIndex)
                    val type = cursor.getString(typeColumnIndex)
                    val color = cursor.getString(colorColumnIndex)
                    val image = cursor.getString(imageColumnIndex)
                    // 他の列も同様に取得
                    val dataModel = YourDataModel(id, name, type, color, image)
                    itemList.add(dataModel)
                }
            }
        }catch (e:Exception){
            Log.d("エラー",e.toString())
        }
        finally {
            cursor.close() // カーソルを閉じる
        }
        return itemList
    }

}