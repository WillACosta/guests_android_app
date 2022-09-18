package com.example.guests.ui.all_guests.view_holder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.guests.data.model.Guest
import com.example.guests.databinding.RowGuestBinding
import com.example.guests.ui.all_guests.listeners.OnGuestListener

class GuestsViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {
    fun bind(guest: Guest) {
        bind.guestName.text = guest.name

        bind.guestName.setOnClickListener {
            listener.onClick(guest.id)
        }

        // improve with drag to exclude
//        bind.guestName.setOnDragListener(object : View.OnDragListener {
//            override fun onDrag(view: View?, drag: DragEvent?): Boolean {
//
//            }
//        })

        bind.buttonExclude.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Removing...")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes") { _, _ -> listener.onDelete(guest.id) }
                .setNegativeButton("No", null)
                .create()
                .show()
        }
    }
}