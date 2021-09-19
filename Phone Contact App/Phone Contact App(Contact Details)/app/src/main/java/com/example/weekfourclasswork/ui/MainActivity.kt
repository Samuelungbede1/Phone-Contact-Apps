package com.example.weekfourclasswork.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SharedMemory.create
import android.widget.Toast

import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weekfourclasswork.R
import com.example.weekfourclasswork.adapter.PlayerAdapter
import com.example.weekfourclasswork.data.DataSource
import com.example.weekfourclasswork.model.PlayerData

class MainActivity : AppCompatActivity(), PlayerAdapter.OnItemClickListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listOfPlayer = DataSource.getPlayers()
        val recyclerView= findViewById<RecyclerView>(R.id.rv_players)
        recyclerView.adapter= PlayerAdapter(listOfPlayer,this)
        recyclerView.layoutManager= LinearLayoutManager(this)
    }

    override fun OnItemClick(player: PlayerData) {
        val intent= Intent(this, PlayerDisplay::class.java )
        //val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, )

        //val imageViewPair = Pair.create<View, String>(imageView, getString("YourTransitionName"))
        intent.putExtra("name", player.name)
        intent.putExtra("mobile", player.mobile)
        intent.putExtra("mail", player.mail)
        intent.putExtra("image", player.image)

        startActivity(intent)

    }

}