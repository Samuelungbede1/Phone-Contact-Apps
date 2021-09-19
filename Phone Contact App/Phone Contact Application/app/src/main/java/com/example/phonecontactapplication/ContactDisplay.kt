package com.example.phonecontactapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactDisplay : AppCompatActivity() {
    var MY_PERMISSIONS_REQUEST_CALL_PHONE = 122

    /** Declaration of the data base variable*/
    private lateinit var database: DatabaseReference
    private lateinit var contact: Contacts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_display)
        contact = intent.getParcelableExtra(CONTACT)!!

        findViewById<TextView>(R.id.tv_ContactDisplayFirstName).text = contact.firstName
        findViewById<TextView>(R.id.tv_ContactDisplayLastName).text = contact.lastName
        findViewById<TextView>(R.id.tv_ContactDisplayPhone).text = contact.number


        /**Implementing the call functionality
        declaring the variable needed*/
        var callButton = findViewById<ImageView>(R.id.iv_callImageButton)
        callButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel: ${contact.number}")
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    MY_PERMISSIONS_REQUEST_CALL_PHONE
                )

            } else
                startActivity(callIntent)
        }


        /**Implementing the Share functionality
        declaring the variable needed*/
        var shareButton = findViewById<ImageView>(R.id.btn_share)
        shareButton.setOnClickListener{

          findViewById<TextView>(R.id.tv_ContactDisplayFirstName).text =contact.firstName
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra("Name: ", "${contact.firstName}")
          //  intent.putExtra("Phone Number: ", "${contact.number}")
            val chooser = Intent.createChooser(intent, "Share to...")
            startActivity(chooser)
        }


        /**Create action Bar,Set Actionbar Title, Back Button*/
        val actionbar = supportActionBar
        actionbar!!.title = "Create new contact"
        actionbar.setDisplayHomeAsUpEnabled(true)


        /**Setting up Delete Function*/
        var deleteButton = findViewById<ImageView>(R.id.btn_deleteContact)
        deleteButton.setOnClickListener {
            deleteContact(contact.id!!)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        /**Setting up the update button, this will take us to the
        Update Contact activity*/

        var editContactButton = findViewById<ImageView>(R.id.btn_editContact)
        editContactButton.setOnClickListener {
            val intent = Intent(this, UpdateContact::class.java)
            intent.putExtra(CONTACT, contact)

            startActivity(intent)
        }

    }

    /**Implementation of the delete button function*/
    fun deleteContact(name: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(name).removeValue().addOnSuccessListener {
            Toast.makeText(this, "Contact Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Operation Failed", Toast.LENGTH_SHORT).show()

        }
    }
}
