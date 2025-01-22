package com.esports.mycontacts.roomdatabase

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.esports.mycontacts.data.Contacts

@Database(entities = [Contacts::class], version = 2)
abstract class ContactDatabase: RoomDatabase() {
   abstract fun contactDao(): ContactDao

}