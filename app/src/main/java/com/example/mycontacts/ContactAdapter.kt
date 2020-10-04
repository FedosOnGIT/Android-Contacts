package com.example.mycontacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact.view.*

class ContactAdapter(
    private val contacts: List<MainActivity.MyContact>,
    private val onClick: (MainActivity.MyContact) -> Unit) :
        RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapter.ContactViewHolder {
        val holder = ContactViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.contact, parent, false)
        )
        holder.root.setOnClickListener {
            onClick(contacts[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactAdapter.ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    inner class ContactViewHolder(val root : View) : RecyclerView.ViewHolder(root) {
        fun bind(contact : MainActivity.MyContact) {
            with(root) {
                phone.text = contact.contact.phoneNumber
                nickname.text = contact.contact.name
                avatar.setImageResource(contact.image)
            }
        }
    }

}
