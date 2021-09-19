package com.example.weekfourclasswork.data

import com.example.weekfourclasswork.R
import com.example.weekfourclasswork.model.PlayerData

object DataSource {

    private val players = listOf(
        PlayerData("Messi","(650) 555-1367","messi@gmail.com", R.drawable.messi10),
        PlayerData("Kelechi","(650) 555-1367","kels@gmail.com", R.drawable.kelechi),
        PlayerData("Ndidi","(650) 555-1367","ndidi@gmail.com", R.drawable.ndidi),
        PlayerData("Ronaldo","(650) 555-1367","ronaldo@gmail.com", R.drawable.ronaldo),
        PlayerData("Samuel","(650) 555-1367","samuel@gmail.com", R.drawable.samuel),
        PlayerData("Xavi","(650) 555-1367","xavi@gmail.com", R.drawable.xavi),
        PlayerData("Mbappe","(650) 555-1367","mbappe@gmail.com", R.drawable.mbape),
        PlayerData("Mane","(650) 555-1367","mane@gmail.com", R.drawable.mane),
        PlayerData("Salah","(650) 555-1367","salah@gmail.com", R.drawable.salah),
        PlayerData("Ramos","(650) 555-1367","ramos@gmail.com", R.drawable.ramos),
        PlayerData("Marcelo","(650) 555-1367","mercelo@gmail.com", R.drawable.mercelo),
        PlayerData("Di Maria","(650) 555-1367","maria@gmail.com", R.drawable.di_maria),
        PlayerData("Kaka","(650) 555-1367","kaka@gmail.com", R.drawable.kaka),
        PlayerData("Pepe","(650) 555-1367","pepe@gmail.com", R.drawable.pepe),
        PlayerData("Lewandoski","(650) 555-1367","lawandoski@gmail.com", R.drawable.lewandoski),
        PlayerData("Suarez","(650) 555-1367","surez@gmail.com", R.drawable.surez),
        PlayerData("Pogba","(650) 555-1367","pogba@gmail.com", R.drawable.pogba),
        PlayerData("Lampard","(650) 555-1367","lampard@gmail.com", R.drawable.lampard),
        PlayerData("Lukaku","(650) 555-1367","lukaku@gmail.com", R.drawable.lukaku),
        PlayerData("Neymar","(650) 555-1367","neymar@gmail.com", R.drawable.neymar),
    )

    fun getPlayers(): List<PlayerData> {
        return players
    }
}