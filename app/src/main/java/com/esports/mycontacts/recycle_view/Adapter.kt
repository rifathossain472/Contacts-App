package com.esports.mycontacts.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esports.mycontacts.data.Contacts
import com.esports.mycontacts.databinding.ItemLayoutBinding

class Adapter(private val contacts: List<Contacts>): RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int= contacts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.binding.apply {
            nameTv.text = contact.name
            phoneTv.text = contact.phone

        }
    }
}