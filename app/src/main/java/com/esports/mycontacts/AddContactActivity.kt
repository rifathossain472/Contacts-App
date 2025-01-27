package com.esports.mycontacts

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.esports.mycontacts.data.Contacts
import com.esports.mycontacts.databinding.ActivityAddContactBinding
import com.esports.mycontacts.roomdatabase.ContactDatabase

class AddContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contactDb"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        val contactDao = db.contactDao()

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val mobile = binding.etMobile.text.toString()
            val address = binding.etAddress.text.toString()
            val contact = Contacts(0,name, email, mobile, address, "https://i.namu.wiki/i/ug032Kpr0TE1IqspQ5rWs7aYg7FTd8NvohQtfLfPz99zmRLGZPKSu1Hj43SbG36K6MEOR6SNotVDl0qP0MRRvg.webp")
            contactDao.insertContact(contact)
            val mainActivityIntent = Intent(this@AddContactActivity, MainActivity::class.java)
            startActivity(mainActivityIntent)
        }




    }
}