package com.example.guests.data.data_sources

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.guests.data.utils.GuestConstants

class GuestDataSource(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

    companion object {
        private const val DB_NAME = "guestDB"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE ${GuestConstants.GuestDataSource.TABLE_NAME} (" +
                    "${GuestConstants.GuestDataSource.COLUMNS.ID} integer primary key autoincrement," +
                    "${GuestConstants.GuestDataSource.COLUMNS.NAME} text," +
                    "${GuestConstants.GuestDataSource.COLUMNS.PRESENCE} integer);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}