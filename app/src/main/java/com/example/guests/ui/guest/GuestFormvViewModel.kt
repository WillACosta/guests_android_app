package com.example.guests.ui.guest


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.guests.data.model.Guest
import com.example.guests.data.repositories.GuestRepository

// AndroidViewModel provides the context
class GuestFormvViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.instance(application)

    fun save(guest: Guest) {
        repository.save(guest)
    }
}