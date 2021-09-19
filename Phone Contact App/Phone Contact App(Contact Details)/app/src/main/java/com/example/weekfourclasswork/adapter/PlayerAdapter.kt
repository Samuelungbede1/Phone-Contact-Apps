package com.example.weekfourclasswork.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weekfourclasswork.model.PlayerData
import com.example.weekfourclasswork.R

class PlayerAdapter (var playerList: List<PlayerData>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameOfPlayer: TextView = view.findViewById(R.id.name_player)
        val imageOfPlayer: ImageView = view.findViewById(R.id.image_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val inflated = inflater.inflate(R.layout.items, parent, false)
        return PlayerViewHolder(inflated)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]
        holder.imageOfPlayer.setImageResource(player.image!!)
        holder.nameOfPlayer.text = player.name

        holder.view.setOnClickListener {
            listener.OnItemClick(player)
        }
    }

    override fun getItemCount(): Int {
        return playerList.size
    }


    interface OnItemClickListener {
        fun OnItemClick(player: PlayerData)
    }
}