package com.example.guests.ui.guest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.guests.data.model.Guest
import com.example.guests.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormvViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[GuestFormvViewModel::class.java]
        binding.radioPresent.isChecked = true

        setListeners()
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener {
            val name = binding.edtName.text.toString()
            val presence = binding.radioPresent.isChecked

            val guest = Guest(0, name, presence)
            viewModel.save(guest)
        }
    }
}