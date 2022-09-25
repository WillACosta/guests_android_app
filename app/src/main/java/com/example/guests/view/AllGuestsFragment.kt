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
import com.example.guests.databinding.FragmentAllGuestsBinding
import com.example.guests.view.adapters.GuestsAdapter
import com.example.guests.view.listeners.OnGuestListener
import com.example.guests.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!

    private val guestAdapter = GuestsAdapter()

    private lateinit var viewModel: GuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[GuestsViewModel::class.java]
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        setListeners()
        configureRecyclerView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getGuests()
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
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GuestDataSource.GUEST_ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.deleteById(id)
                viewModel.getGuests()
            }
        }

        guestAdapter.attachListener(listener)
    }
}