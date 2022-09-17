package com.example.guests.ui.all_guests

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guests.data.model.Guest
import com.example.guests.data.repositories.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.instance(application.applicationContext)
    private val _allGuests = MutableLiveData<List<Guest>>()

    val guests: LiveData<List<Guest>> = _allGuests

    fun getAll() {
        _allGuests.value = repository.getAll()
    }

    fun deleteById(id: Int) {
        repository.delete(id)
    }
}