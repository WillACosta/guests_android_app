package com.example.guests.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.data.model.Guest
import com.example.guests.databinding.RowGuestBinding
import com.example.guests.view.listeners.OnGuestListener
import com.example.guests.view.view_holder.GuestsViewHolder

class GuestsAdapter : RecyclerView.Adapter<GuestsViewHolder>() {
    private var guestsList: List<Guest> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        // inflate row ui with parent context
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestsViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        holder.bind(guestsList[position])
    }

    override fun getItemCount(): Int {
        return guestsList.count()
    }

    fun updateGuests(guests: List<Guest>) {
        guestsList = guests
        notifyDataSetChanged() // notify recyclerView for new data
    }

    fun attachListener(l: OnGuestListener) {
        listener = l
    }
}