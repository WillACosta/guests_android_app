package com.example.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.data.utils.GuestConstants
import com.example.guests.databinding.FragmentPresentBinding
import com.example.guests.view.adapters.GuestsAdapter
import com.example.guests.view.listeners.OnGuestListener
import com.example.guests.viewmodel.GuestsViewModel

class PresentFragment : Fragment() {

    private lateinit var viewModel: GuestsViewModel

    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!
    private val guestAdapter = GuestsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[GuestsViewModel::class.java]
        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        setListeners()
        configureRecyclerView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getPresentGuests()
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
        binding.recyclerPresents.layoutManager = LinearLayoutManager(context)
        binding.recyclerPresents.adapter = guestAdapter

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
        viewModel.getGuests(GuestConstants.FILTER.PRESENTS)
    }
}