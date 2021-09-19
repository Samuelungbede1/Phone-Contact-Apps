package com.example.phonecontactreader

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView



class ContactAdapter(var contactList: List<ContactDetails>, var context: Context) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

       class ContactViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var nameOfContact: TextView = view.findViewById(R.id.tv_ContactName)!!
        var phoneNumber: TextView = view.findViewById(R.id.tv_PhoneNumber)!!
        var contactImage: ImageView = view.findViewById(R.id.iv_contactImage)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflated = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(inflated)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var contact = contactList[position]
        holder.nameOfContact.text = contact.name
        holder.phoneNumber.text = contact.mobileNumber


        if (contactList[position].image != null)
            holder.contactImage.setImageBitmap(contactList[position].image)
            else
                holder.contactImage.setImageDrawable(ContextCompat.getDrawable(context,R.mipmap.ic_launcher_round))

    }


    override fun getItemCount(): Int {
        return contactList.size
    }
}

