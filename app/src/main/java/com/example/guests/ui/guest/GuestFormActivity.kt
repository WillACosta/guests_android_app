package com.example.guests.ui.guest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.guests.data.model.Guest
import com.example.guests.data.utils.GuestConstants
import com.example.guests.databinding.ActivityGuestFormBinding
import com.google.android.material.snackbar.Snackbar

class GuestFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormvViewModel

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormvViewModel::class.java]
        binding.radioPresent.isChecked = true

        setListeners()
        loadData()
    }

    private fun loadData() {
        val bundle = intent.extras

        if (bundle != null) {
            val id = bundle.getInt(GuestConstants.GuestDataSource.GUEST_ID)
            viewModel.getGuestById(id)
        }
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val presence = binding.radioPresent.isChecked

            val guest = Guest(guestId, name, presence)
            viewModel.save(guest)
            finish()
        }

        viewModel.guest.observe(this, {
            binding.edtName.setText(it.name)

            if (it.presence) binding.radioPresent.isChecked = true
            else binding.radioPresent.isChecked = true
        })

        viewModel.isSaved.observe(this, {
            if (it.isSuccess) {
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }
}