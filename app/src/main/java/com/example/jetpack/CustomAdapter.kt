package com.example.jetpack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject


class CustomAdapter(val contactList: ArrayList<Contact>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact: Contact = contactList[position]
        holder?.bindItems(contactList[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(contact: Contact) {
            val id = itemView.findViewById<TextView>(R.id.id)
            val name = itemView.findViewById<TextView>(R.id.name)
            val email = itemView.findViewById<TextView>(R.id.email)
            val address = itemView.findViewById<TextView>(R.id.address)
            val gender = itemView.findViewById<TextView>(R.id.gender)
            val mobile = itemView.findViewById<TextView>(R.id.mobile)
            val home = itemView.findViewById<TextView>(R.id.home)
            val office = itemView.findViewById<TextView>(R.id.office)
            id.text = contact.id
            name.text = contact.name
            email.text = contact.email
            address.text = contact.address
            gender.text = contact.gender
            mobile.text = contact.mobile
            home.text = contact.home
            office.text = contact.office
        }


    }




}