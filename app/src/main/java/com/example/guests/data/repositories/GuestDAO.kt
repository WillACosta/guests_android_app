package com.example.guests.data.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.guests.data.model.Guest

@Dao
interface GuestDAO {
    @Insert
    fun save(guest: Guest): Long

    @Update
    fun update(guest: Guest): Int

    @Delete
    fun delete(guest: Guest)

    @Query("SELECT * FROM GUEST WHERE id = :id")
    fun getById(id: Int): Guest

    @Query("SELECT * FROM GUEST")
    fun getAll(): List<Guest>

    @Query("SELECT * FROM GUEST WHERE presence = 1")
    fun getPresents(): List<Guest>

    @Query("SELECT * FROM GUEST WHERE presence = 0")
    fun getAbsents(): List<Guest>
}