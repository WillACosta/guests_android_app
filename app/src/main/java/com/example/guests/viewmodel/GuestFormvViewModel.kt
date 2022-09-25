package com.example.guests.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.guests.data.model.Guest
import com.example.guests.data.model.SuccessFailure
import com.example.guests.data.repositories.GuestRepository

// AndroidViewModel provides the context
class GuestFormvViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)

    // We can use LiveData to observa repository response
    private val _guest = MutableLiveData<Guest>()
    val guest: LiveData<Guest> = _guest

    private val _isSaved = MutableLiveData<SuccessFailure>()
    val isSaved: LiveData<SuccessFailure> = _isSaved

    fun save(guest: Guest) {
        val succesFailure = SuccessFailure(true, "Guest is saved :)")

        if (guest.id == 0) {
            succesFailure.isSuccess = repository.save(guest)
        } else {
            succesFailure.isSuccess = repository.update(guest)
        }
    }

    fun getAll() {
        repository.getAll()
    }

    fun getGuestById(id: Int) {
        _guest.value = repository.getGuestById(id)
    }
}