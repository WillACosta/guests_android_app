package com.example.guests.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guests.data.model.Guest
import com.example.guests.data.repositories.GuestRepository
import com.example.guests.data.utils.GuestConstants

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)
    private val _guestsList = MutableLiveData<List<Guest>>()

    val guests: LiveData<List<Guest>> = _guestsList

    fun getGuests(type: Int = 0) {
        when (type) {
            GuestConstants.FILTER.ABSENTS -> {
                _guestsList.value = repository.getAbsent()
            }

            GuestConstants.FILTER.PRESENTS -> {
                _guestsList.value =  repository.getPresent()
            }

            else -> {
                _guestsList.value =  repository.getAll()
            }
        }
    }

    fun deleteById(id: Int) {
        repository.delete(id)
    }
}