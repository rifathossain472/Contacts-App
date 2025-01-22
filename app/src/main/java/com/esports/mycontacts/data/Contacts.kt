package com.esports.mycontacts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contacts(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val img: String
)