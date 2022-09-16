package com.example.guests.data.repositories

import android.content.ContentValues
import android.content.Context
import com.example.guests.data.data_sources.GuestDataSource
import com.example.guests.data.model.Guest

class GuestRepository private constructor(context: Context) {
    private val dataSource = GuestDataSource(context)

    companion object {
        private lateinit var repository: GuestRepository

        // Get an Singleton instance of class
        fun instance(context: Context): GuestRepository {
            if (Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }

            return repository
        }
    }

    fun save(guest: Guest): Boolean {
        return try {
            val db = dataSource.writableDatabase

            val values = ContentValues()
            values.put("name", guest.name)
            values.put("presence", guest.presence)

            db.insert("Guest", null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

}