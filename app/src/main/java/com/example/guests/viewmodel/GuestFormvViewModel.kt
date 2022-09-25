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
        val succesFailure = SuccessFailure(false, "")

        if (guest.id == 0) {
            succesFailure.success = repository.save(guest)
            succesFailure.message = "Guest is saved :)"

            _isSaved.value = succesFailure
        } else {
            succesFailure.success = repository.update(guest)
            succesFailure.message = "Guest is updated :)"

            _isSaved.value = succesFailure
        }
    }

    fun getAll() {
        repository.getAll()
    }

    fun getGuestById(id: Int) {
        _guest.value = repository.getGuestById(id)
    }
}