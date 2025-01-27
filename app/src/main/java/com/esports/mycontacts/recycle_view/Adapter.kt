package com.esports.mycontacts.recycle_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.esports.mycontacts.R
import com.esports.mycontacts.data.Contacts
import com.esports.mycontacts.databinding.ItemLayoutBinding

class Adapter(
    private var contacts: List<Contacts>,
    private val listener: HandleClickListener,
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(contacts: List<Contacts>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    interface HandleClickListener {
        fun deleteClickListener(contacts: Contacts)
        fun editClickListener(contacts: Contacts)
        fun favoriteClickListener(contacts: Contacts, isAdded: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.binding.apply {
            nameTv.text = contact.name
            phoneTv.text = contact.phone

            // Set favorite icon color based on the status
            val favoriteIconColor = if (contact.isFavorite) {
                ContextCompat.getColor(favoriteIv.context, R.color.red)
            } else {
                ContextCompat.getColor(favoriteIv.context, R.color.black)
            }
            favoriteIv.setColorFilter(favoriteIconColor)

            // Handle delete action
            deleteIv.setOnClickListener {
                listener.deleteClickListener(contact)
            }

            // Handle favorite toggle
            favoriteIv.setOnClickListener {
                val newFavoriteStatus = !contact.isFavorite
                listener.favoriteClickListener(contact, newFavoriteStatus) // Notify activity
            }
        }
    }
}
