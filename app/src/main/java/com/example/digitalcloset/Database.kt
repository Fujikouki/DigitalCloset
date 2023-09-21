package com.example.digitalcloset

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder

class Database(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{

        private const val  DATABASE_NAME = "clothes.db"

        private const val DATABASE_VERSION = 1

    }
    override fun onCreate(db:SQLiteDatabase){


        val sb = StringBuilder()
        sb.append("CREATE TABLE clothesmemos (")
        sb.append("_id INTEGER PRIMARY KEY,")
        sb.append("clothes_name TEXT,")
        sb.append("clothes_type TEXT,")
        sb.append("clothes_color TEXT")
        sb.append(");")
        val sql = sb.toString()
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}