package com.example.phonecontactapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import org.json.JSONObject

const val CONTACT = "Contact"

class MainActivity : AppCompatActivity(), ListAdapter.OnItemClickListener {


    /**Declaring field variables to
    be used later in the code*/

    private lateinit var dref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<Contacts>
    private lateinit var userAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userRecyclerView = findViewById(R.id.rv_RecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userArrayList = arrayListOf()
        userAdapter = ListAdapter(userArrayList, this)

        /** Setting up the button and thereafter calling the Onclick listener*/
        var floatButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatButton.setOnClickListener {
            val intent = Intent(this, AddNewContact::class.java)
            startActivity(intent)
        }

        /**This function will get user data from the FireBase DataBase*/
        getUserData()


    }

    /** Implementation of the function to get user data from the FireBase DataBase*/
    private fun getUserData() {
        dref = FirebaseDatabase.getInstance().getReference("Users")
        dref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    userArrayList.clear()
                    for (userSnapShot in snapshot.children) {
                        val user = userSnapShot.getValue(Contacts::class.java)
                        if (user != null) {
                            userArrayList.add(user)
                            Log.d("USER", user.toString())
                        }
                        userRecyclerView.adapter = userAdapter
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    /** Implementation of the function when an item on the
     * Recycler view is clicked*/
    override fun OnItemClick(contacts: Contacts) {
        val intent = Intent(this, ContactDisplay::class.java)
        intent.putExtra(CONTACT, contacts)
        startActivity(intent)
    }
}