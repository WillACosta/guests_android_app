package com.example.guests.view.view_holder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.data.model.Guest
import com.example.guests.databinding.RowGuestBinding
import com.example.guests.view.listeners.OnGuestListener

class GuestsViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {
    fun bind(guest: Guest) {
        bind.guestName.text = guest.name

        bind.guestName.setOnClickListener {
            listener.onClick(guest.id)
        }

        bind.buttonExclude.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Delete guest")
                .setMessage("Are you sure to delete this person?\nYou can't undo this action.")
                .setPositiveButton("Delete") { _, _ -> listener.onDelete(guest.id) }
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }
    }
}