package com.example.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.data.utils.GuestConstants
import com.example.guests.databinding.FragmentAbsentBinding
import com.example.guests.view.adapters.GuestsAdapter
import com.example.guests.view.listeners.OnGuestListener
import com.example.guests.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private lateinit var viewModel: GuestsViewModel
    private var _binding: FragmentAbsentBinding? = null

    private val guestAdapter = GuestsAdapter()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[GuestsViewModel::class.java]
        _binding = FragmentAbsentBinding.inflate(inflater, container, false)
        setListeners()
        configureRecyclerView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        getPresentGuests()
    }

    private fun setListeners() {
        viewModel.guests.observe(viewLifecycleOwner) {
            guestAdapter.updateGuests(it)
        }
    }

    private fun configureRecyclerView() {
        binding.recyclerAbsents.layoutManager = LinearLayoutManager(context)
        binding.recyclerAbsents.adapter = guestAdapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GuestDataSource.GUEST_ID, id)
                intent.putExtras(bundle)
            }

            override fun onDelete(id: Int) {
                viewModel.deleteById(id)
                getPresentGuests()
            }
        }

        guestAdapter.attachListener(listener)
    }

    private fun getPresentGuests() {
        viewModel.getGuests(GuestConstants.FILTER.ABSENTS)
    }
}