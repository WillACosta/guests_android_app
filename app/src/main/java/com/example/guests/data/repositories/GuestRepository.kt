package com.example.guests.data.repositories

import android.content.ContentValues
import android.content.Context
import com.example.guests.data.data_sources.GuestDataSource
import com.example.guests.data.model.Guest
import com.example.guests.data.utils.GuestConstants

class GuestRepository private constructor(context: Context) {
    private val dataSource = GuestDataSource(context)

    companion object {
        private lateinit var repository: GuestRepository

        // Get an Singleton instance of class
        fun instance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }

            return repository
        }
    }

    fun save(guest: Guest): Boolean {
        return try {
            val db = dataSource.writableDatabase

            val values = ContentValues()
            values.put(GuestConstants.GuestDataSource.COLUMNS.NAME, guest.name)
            values.put(GuestConstants.GuestDataSource.COLUMNS.PRESENCE, guest.presence)

            db.insert(
                GuestConstants.GuestDataSource.TABLE_NAME, null, values
            )
            true
        } catch (e: Exception) {
            false
        }
    }

}