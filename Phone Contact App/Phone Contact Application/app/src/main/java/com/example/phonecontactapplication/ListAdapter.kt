package com.example.phonecontactapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/** Implementation of the Adapter Class that connects ArrayList of contacts to the Recycler View*/
class ListAdapter(var listOfContact: ArrayList<Contacts>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    /** Implementation of the Item view holder class*/
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var firstName = itemView.findViewById<TextView>(R.id.tv_ContactFirstName)
        var lastName = itemView.findViewById<TextView>(R.id.tv_ContactLastName)
        var phone = itemView.findViewById<TextView>(R.id.tv_PhoneNumber)

    }

    /** Implementation of the Item view holder function*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        )
    }

    /** Implementation of the onBindViewHolder Function*/
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var currentItem = listOfContact[position]
        holder.firstName.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName
        holder.phone.text = currentItem.number
        holder.itemView.setOnClickListener {
            listener.OnItemClick(currentItem)
        }


    }

    override fun getItemCount(): Int {
        return listOfContact.size
    }


    /** Implementation of the  interface class that contains the OnItem Click function*/
    interface OnItemClickListener {
        fun OnItemClick(contacts: Contacts)
    }
}