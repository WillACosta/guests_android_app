package com.example.guests.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.guests.data.model.Guest
import com.example.guests.data.repositories.GuestDAO
import com.example.guests.data.utils.GuestConstants

@Database(entities = [Guest::class], version = 1)
abstract class GuestLocalDataSource : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    companion object {
        private lateinit var _instance: GuestLocalDataSource

        fun instance(context: Context): GuestLocalDataSource {
            if (!Companion::_instance.isInitialized) {

                // execute the following instruction in only thread
                synchronized(GuestLocalDataSource::class) {
                    _instance = Room.databaseBuilder(
                        context,
                        GuestLocalDataSource::class.java,
                        GuestConstants.GuestDataSource.TABLE_NAME
                    )
                        .addMigrations()
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return _instance
        }
    }
}