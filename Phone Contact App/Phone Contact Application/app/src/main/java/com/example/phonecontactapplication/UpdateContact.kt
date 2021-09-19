package com.example.phonecontactapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateContact: AppCompatActivity() {
    private lateinit var user : Contacts

    private lateinit var dataBase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_contact)


        /**Receiving intent from the ContactDisplay Activity */
        user = intent.getParcelableExtra(CONTACT)!!


        /** getting all the edit text id and setting them as the values of the
         firstName lastName and phone for editing */
        findViewById<EditText>(R.id.et_editFirstName).setText(user.firstName).toString()
        findViewById<EditText>(R.id.et_editLastName).setText(user.lastName).toString()
        findViewById<EditText>(R.id.et_editPhoneNumber).setText(user.number).toString()


        /** Initializing the update button, calling the update contact function */
        var updateButton = findViewById<Button>(R.id.btn_updateContactDetails)

        updateButton.setOnClickListener{
            var newFirstName =  findViewById<EditText>(R.id.et_editFirstName).text.toString()
            var newLastName =  findViewById<EditText>(R.id.et_editLastName).text.toString()
            var newPhone = findViewById<EditText>(R.id.et_editPhoneNumber).text.toString()

            updateContactDetails(newFirstName,newLastName,newPhone)
        }

    }
    /** Implementation of the update contact function*/
    fun updateContactDetails(editedFirstName: String, editedLastName: String, editedphone: String){
        dataBase =  FirebaseDatabase.getInstance().getReference("Users")
        val newUser =  mapOf(
            "firstName" to editedFirstName,
            "lastName" to editedLastName,
            "number" to editedphone
        )

        dataBase.child(user.id!!).updateChildren(newUser).addOnSuccessListener {
           findViewById<EditText>(R.id.et_editFirstName).text.clear()
            findViewById<EditText>(R.id.et_editLastName).text.clear()
           findViewById<EditText>(R.id.et_editPhoneNumber).text.clear()
            Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()

        }
    }
}