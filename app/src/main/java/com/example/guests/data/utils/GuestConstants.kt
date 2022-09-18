package com.example.guests.data.utils

abstract class GuestConstants {
    object GuestDataSource {
        const val GUEST_ID = "guestId"
        const val TABLE_NAME = "Guest"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PRESENCE = "presence"
        }
    }
}