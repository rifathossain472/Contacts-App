package com.esports.mycontacts

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.esports.mycontacts.data.Contacts
import com.esports.mycontacts.databinding.ActivityFavoriteBinding
import com.esports.mycontacts.databinding.FavoriteItemLayoutBinding
import com.esports.mycontacts.recycle_view.Adapter
import com.esports.mycontacts.recycle_view.FavoriteAdapter

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter
    private lateinit var favoriteContacts: MutableList<Contacts>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteContacts =
            (intent.getSerializableExtra("favoriteContacts") as? ArrayList<Contacts>)?.toMutableList()
                ?: mutableListOf()
        if (favoriteContacts.isEmpty()) {
            binding.noDataTv.visibility = View.VISIBLE
        } else {
            adapter = FavoriteAdapter(favoriteContacts)
            binding.rvLayout.adapter = adapter
        }

    }
}