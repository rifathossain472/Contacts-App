package com.esports.mycontacts.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esports.mycontacts.data.Contacts

@Database(entities = [Contacts::class], version = 3)
abstract class ContactDatabase: RoomDatabase() {
   abstract fun contactDao(): ContactDao

}