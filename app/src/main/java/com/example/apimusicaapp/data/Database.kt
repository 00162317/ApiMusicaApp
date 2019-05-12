package com.example.apimusicaapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

private const val SQL_CREATE_ENTRIES="CREATE TABLE ${DatabaseContract.CancionEntry.TABLE_NAME} (" +
        "${BaseColumns._ID} INTEGER PRIMARY KEY," +
        "${DatabaseContract.CancionEntry.COLUMN_NAME} TEXT," +
        "${DatabaseContract.CancionEntry.COLUMN_ARTISTA} TEXT," +
        "${DatabaseContract.CancionEntry.COLUMN_ANIO} TEXT," +
        "${DatabaseContract.CancionEntry.COLUMN_ALBUM} TEXT)"

private const val SQL_DELETE_ENTRIES=
    "DROP TABLE IF EXISTS ${DatabaseContract.CancionEntry.TABLE_NAME}"

class Database(context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(p0)
    }

    fun check(db:SQLiteDatabase){
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME="miprimerabase.db"
        const val DATABASE_VERSION=1
    }

}