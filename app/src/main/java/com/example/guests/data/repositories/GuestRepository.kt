package com.example.guests.data.repositories

import android.content.Context
import com.example.guests.data.data_sources.GuestLocalDataSource
import com.example.guests.data.model.Guest

class GuestRepository(context: Context) {
    private val dataSource = GuestLocalDataSource.instance(context).guestDAO()

    fun save(guest: Guest): Boolean {
        return dataSource.save(guest) > 0
    }

    fun update(guest: Guest): Boolean {
        return dataSource.update(guest) > 0
    }

    fun delete(id: Int) {
        val guest = getGuestById(id)
        dataSource.delete(guest)
    }

    fun getGuestById(id: Int): Guest {
        return dataSource.getById(id)
    }

    fun getAll(): List<Guest> {
        return dataSource.getAll()
    }

    fun getPresent(): List<Guest> {
        return dataSource.getPresents()
    }

    fun getAbsent(): List<Guest> {
        return dataSource.getAbsents()
    }
}