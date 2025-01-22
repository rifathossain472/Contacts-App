package com.esports.mycontacts

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.esports.mycontacts.data.Contacts
import com.esports.mycontacts.databinding.ActivityMainBinding
import com.esports.mycontacts.recycle_view.Adapter
import com.esports.mycontacts.roomdatabase.ContactDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contactDb"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        val contactDao = db.contactDao()

        val contactList = contactDao.getAllContacts()

        val adapter = Adapter(contactList)
        binding.rvLayout.adapter = adapter

        binding.btnAddUser.setOnClickListener {
            val addContactIntent = Intent(this@MainActivity, AddContactActivity::class.java)
            startActivity(addContactIntent)
        }






        /*val contact = listOf<Contacts>(
            Contacts("Hamza Chowdhury", "+3216549870", "https://i.namu.wiki/i/ug032Kpr0TE1IqspQ5rWs7aYg7FTd8NvohQtfLfPz99zmRLGZPKSu1Hj43SbG36K6MEOR6SNotVDl0qP0MRRvg.webp"),
            Contacts("Eve Davis", "+6549873210", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiCSjagPIC1aH5jOEd1RarshRUaG02SSscFg&s"),
            Contacts("Frank Moore", "+7891234560", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCPbba9N2FFRUcddA4XRLWfJVULDtE4cwM7A&s"),
            Contacts("David Wilson", "+3216549870", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNlfHrfaskACki_87OhkyPnwDVkYL-6PAQog&s"),
            Contacts("Eve Davis", "+6549873210", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSc0Jko_zXr3PU-ogsy210LQL3vnqnoHF0YCw&s"),
            Contacts("Frank Moore", "+7891234560", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwr8g7Gv-ZjAAwNQIUG13xSf3f6Usujf3tUg&s"),
            Contacts("Grace Lee", "+1593574860", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_tYtWwJUJHbwZk3ftTAYcYy0DYaJlElqWNw&s"),
            Contacts("Hannah White", "+7539514860", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQkk8IflHfHJJcTkgV5uhL30z1EowP0B2j5w&s"),
            Contacts("Ivy Clark", "+2589631470", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2c0VmJst93XVqiNjAnsHULfi_f-eRPst8wg&s"),
            Contacts("Jack Hall", "+1478523690", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6KBe81WoWEWCrKuhbKBdQfgxB4M4QGrGdDQ&s"),
            Contacts("Karen Adams", "+3692581470", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmeY45mgVNPGtLjwj2ST8A1pPkgj6PEGZqdw&s"),
            Contacts("Leo Turner", "+7418529630", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdCbas2zWRFJCIaDx0TUoACmC1a96D2abrUQ&s"),
            Contacts("Mia Wright", "+8529637410", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQFcZQoi-Q9hbTtfBlCKFCWmxNufpdWI_WBg&s"),
            Contacts("Noah Harris", "+9637418520", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfdelvgodUl29M_xJE9oGCtLg75W_Qz993wQ&s"),
            Contacts("Olivia Martin", "+1237894560", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSnq9QwKHzjsh_QOP2kUaubzuOqRgjhAlWaw&s"),
            Contacts("Paul Lewis", "+4569871230", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSnq9QwKHzjsh_QOP2kUaubzuOqRgjhAlWaw&s"),
            Contacts("Quinn Walker", "+7894561230", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSnq9QwKHzjsh_QOP2kUaubzuOqRgjhAlWaw&s"),
            Contacts("Steve King", "+9632587410", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhXScAMub_UEyZp0qPClPwIlYP8ScY3eTJYA&s"),
            Contacts("Steve King", "+9632587410", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhXScAMub_UEyZp0qPClPwIlYP8ScY3eTJYA&s"),
            Contacts("Tom Baker", "+7896541230", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXa4gMLKAzwEpjNIAQOnYNbRoGkpbFiFp1GA&s"),
            Contacts("Uma Fisher", "+4563217890", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRfy0UraEFfrUQl_JHLUtv9UfXY-kMXz8Q4Q&s"),
            Contacts("Vera Green", "+7419638520", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkz3VIcWKN0z7pUo9M7B9EVMFA5GfWYVCDvA&s")
        )*/

    }
}