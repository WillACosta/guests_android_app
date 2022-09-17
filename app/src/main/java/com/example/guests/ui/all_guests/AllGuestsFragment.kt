package com.example.guests.ui.all_guests

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.databinding.FragmentAllGuestsBinding
import com.example.guests.ui.all_guests.adapters.GuestsAdapter
import com.example.guests.ui.all_guests.listeners.OnGuestListener
import com.example.guests.ui.guest.GuestFormActivity

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!

    private val guestAdapter = GuestsAdapter()

    private lateinit var viewModel: AllGuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AllGuestsViewModel::class.java]
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        setListeners()
        configureRecyclerView()

        viewModel.getAll() // refactor: change location of this
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        viewModel.guests.observe(viewLifecycleOwner) {
            guestAdapter.updateGuests(it)
        }
    }

    private fun configureRecyclerView() {
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllGuests.adapter = guestAdapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                // go to guest form activity and update data
                // pass extra to intent

                // val intent = Intent(context, GuestFormActivity::class.java)
                // intent.putExtra('guest', null)
            }

            override fun onDelete(id: Int) {
                viewModel.deleteById(id)
                viewModel.getAll()
            }
        }

        guestAdapter.attachListener(listener)
    }
}