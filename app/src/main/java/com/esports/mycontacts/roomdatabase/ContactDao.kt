package com.esports.mycontacts.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.esports.mycontacts.data.Contacts

@Dao
interface ContactDao {
    @Insert
    fun getAddContact(contacts: Contacts)

    @Query("SELECT * FROM Contacts")
    fun getAllContacts(): List<Contacts>
}