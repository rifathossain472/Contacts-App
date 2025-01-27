package com.esports.mycontacts.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.esports.mycontacts.data.Contacts

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contacts: Contacts)

    @Query("SELECT * FROM Contacts")
    fun getAllContacts(): MutableList<Contacts>

    @Query("SELECT * FROM Contacts WHERE isFavorite = 1")
    fun getFavoriteContacts(): MutableList<Contacts>

    @Delete
    fun deleteContact(contacts: Contacts)

    @Query("UPDATE Contacts SET isFavorite = :isFavorite WHERE id = :contactId")
    fun updateFavoriteStatus(contactId: Int, isFavorite: Boolean)
}
