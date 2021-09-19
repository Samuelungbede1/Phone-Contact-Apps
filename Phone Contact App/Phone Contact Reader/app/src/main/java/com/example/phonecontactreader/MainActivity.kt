package com.example.phonecontactreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonDisplayContact = findViewById<Button>(R.id.btn_ReadContacts)

        val listOfContact = findViewById<RecyclerView>(R.id.rv_contacts)
        listOfContact.layoutManager = LinearLayoutManager(this)



        buttonDisplayContact.setOnClickListener {
            val contactList: MutableList<ContactDetails> = ArrayList()
            val phoneBook = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null
            )
            while (phoneBook!!.moveToNext()) {
                val name =
                    phoneBook.getString(phoneBook.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    phoneBook.getString(phoneBook.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                val objectOfContact = ContactDetails(name, number)

                val contactPhoto =
                    phoneBook.getString(phoneBook.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))
                if (contactPhoto != null) {
                    objectOfContact.image =
                        MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(contactPhoto))
                }
                contactList.add(objectOfContact)

            }

            listOfContact.adapter = ContactAdapter(contactList, this)
            phoneBook.close()

        }
    }
}