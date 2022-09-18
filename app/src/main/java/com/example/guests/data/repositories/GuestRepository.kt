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
            val values = getDataBaseContentValues(guest)

            db.insert(
                GuestConstants.GuestDataSource.TABLE_NAME, null, values
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: Guest): Boolean {
        return try {
            val db = dataSource.writableDatabase

            val values = getDataBaseContentValues(guest)

            val selection = "${GuestConstants.GuestDataSource.COLUMNS.ID} = ?"
            val args = arrayOf(guest.id.toString())

            db.update(
                GuestConstants.GuestDataSource.TABLE_NAME, values, selection, args
            )

            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = dataSource.writableDatabase

            val selection = "${GuestConstants.GuestDataSource.COLUMNS.ID} = ?"
            val args = arrayOf(id.toString())

            db.delete(
                GuestConstants.GuestDataSource.TABLE_NAME,
                selection,
                args
            )

            true
        } catch (e: Exception) {
            false
        }
    }

    private fun getDataBaseContentValues(guest: Guest): ContentValues {
        val values = ContentValues()

        values.put(GuestConstants.GuestDataSource.COLUMNS.NAME, guest.name)
        values.put(GuestConstants.GuestDataSource.COLUMNS.PRESENCE, guest.presence)

        return values
    }

    fun getAll(): List<Guest> {
        val list: MutableList<Guest> = ArrayList()

        return try {
            val db = dataSource.readableDatabase

            // define the columns that we need
            val projection = arrayOf(
                GuestConstants.GuestDataSource.COLUMNS.ID,
                GuestConstants.GuestDataSource.COLUMNS.NAME,
                GuestConstants.GuestDataSource.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                GuestConstants.GuestDataSource.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            // if has any data in table
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.ID))

                    val name =
                        cursor.getString(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.NAME))

                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.PRESENCE)) == 1)

                    list.add(Guest(id, name, presence))
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getPresent(): List<Guest> {
        val list: MutableList<Guest> = ArrayList()
        return try {
            val db = dataSource.readableDatabase

            val cursor =
                db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.ID))

                    val name =
                        cursor.getString(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.NAME))

                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.PRESENCE)) == 1)

                    list.add(Guest(id, name, presence))
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getAbsent(): List<Guest> {
        val list: MutableList<Guest> = ArrayList()
        return try {
            val db = dataSource.readableDatabase

            val cursor =
                db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0", null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.ID))

                    val name =
                        cursor.getString(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.NAME))

                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.PRESENCE)) == 1)

                    list.add(Guest(id, name, presence))
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getGuestById(id: Int): Guest? {

        var guest: Guest? = null

        return try {
            val db = dataSource.readableDatabase

            val projection = arrayOf(
                GuestConstants.GuestDataSource.COLUMNS.NAME,
                GuestConstants.GuestDataSource.COLUMNS.PRESENCE
            )

            val selection = GuestConstants.GuestDataSource.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                GuestConstants.GuestDataSource.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                val name =
                    cursor.getString(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.NAME))
                val presence =
                    (cursor.getInt(cursor.getColumnIndex(GuestConstants.GuestDataSource.COLUMNS.PRESENCE)) == 1)

                guest = Guest(id, name, presence)
            }

            cursor?.close()
            guest
        } catch (e: Exception) {
            guest
        }
    }
}