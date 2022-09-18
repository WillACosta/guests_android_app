package com.example.guests.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.guests.data.utils.GuestConstants

@Entity(tableName = GuestConstants.GuestDataSource.TABLE_NAME)
class Guest() {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Int = 0

    @ColumnInfo
    var name: String = ""

    @ColumnInfo
    var presence: Boolean = false
}