package com.esports.mycontacts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.room.Room
import com.esports.mycontacts.data.Contacts
import com.esports.mycontacts.databinding.ActivityMainBinding
import com.esports.mycontacts.recycle_view.Adapter
import com.esports.mycontacts.roomdatabase.ContactDao
import com.esports.mycontacts.roomdatabase.ContactDatabase
import java.util.Locale

class MainActivity : AppCompatActivity(), Adapter.HandleClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private lateinit var contactsList: MutableList<Contacts>
    private lateinit var favoriteContactList: MutableList<Contacts>
    private lateinit var contactDao: ContactDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contactDb"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        contactDao = db.contactDao()

        fetchContacts() // Fetch all contacts and favorites
        setHomePage()

        // Search functionality
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        // Add contact button
        binding.btnAddUser.setOnClickListener {
            val addContactIntent = Intent(this@MainActivity, AddContactActivity::class.java)
            startActivity(addContactIntent)
        }

        // Navigate to FavoriteActivity
        binding.btnFavorites.setOnClickListener {
            val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
            intent.putExtra("favoriteContacts", ArrayList(favoriteContactList))
            startActivity(intent)
        }
    }

    private fun setHomePage() {
        adapter = Adapter(contactsList, this@MainActivity)
        binding.rvLayout.adapter = adapter
    }

    private fun fetchContacts() {
        contactsList = contactDao.getAllContacts().toMutableList()
        favoriteContactList = contactsList.filter { it.isFavorite }.toMutableList()
    }

    fun filterList(query: String?) {
        if (query != null) {
            val filteredList = contactsList.filter {
                it.name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))
            }
            adapter.setFilteredList(filteredList)
        }
    }

    override fun deleteClickListener(contacts: Contacts) {
        contactDao.deleteContact(contacts)
        Toast.makeText(this, "Contact Deleted Successfully", Toast.LENGTH_SHORT).show()
        fetchContacts()
        setHomePage()
    }

    override fun editClickListener(contacts: Contacts) {
        // Handle edit action if needed
    }

    override fun favoriteClickListener(contacts: Contacts, isAdded: Boolean) {
        // Update the favorite status in the database
        contactDao.updateFavoriteStatus(contacts.id, isAdded)

        // Refresh the contacts list
        fetchContacts()
        setHomePage()

        // Show toast message
        val message = if (isAdded) {
            "${contacts.name} added to favorites"
        } else {
            "${contacts.name} removed from favorites"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
