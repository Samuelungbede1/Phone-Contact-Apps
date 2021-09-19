package com.example.phonecontactapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.phonecontactapplication.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNewContact : AppCompatActivity() {

    private lateinit var dataBase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_contact)

        //Initializes add button variable
        var addButton = findViewById<Button>(R.id.btn_add)
        var firstName = findViewById<EditText>(R.id.etFirstName)
        var lastName = findViewById<EditText>(R.id.etLastName)
        var phone = findViewById<EditText>(R.id.etPhone)


        //Create action Bar,Set Actionbar Title, Back Button
        val actionbar = supportActionBar
        actionbar!!.title = "Create new contact"
        actionbar.setDisplayHomeAsUpEnabled(true)


        //Add Button will take First Name, Last Name and Phone to the recycler view
        addButton.setOnClickListener {
            var contactFirstName = firstName.text.toString().trim()
            var contactLastName = lastName.text.toString().trim()
            var contactPhone = phone.text.toString().trim()

            dataBase = FirebaseDatabase.getInstance().getReference("Users")
            val user = Contacts(
                firstName = contactFirstName,
                lastName = contactLastName,
                number = contactPhone
            )
            user.id = dataBase.push().key
            dataBase.child(user.id!!).setValue(user).addOnSuccessListener {

                firstName.text.clear()
                lastName.text.clear()
                phone.text.clear()

                Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }


    // Back Button in the create new contact Activity
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}