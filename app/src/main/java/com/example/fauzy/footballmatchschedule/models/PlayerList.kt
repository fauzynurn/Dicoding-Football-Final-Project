package com.example.fauzy.footballmatchschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PlayerList{
    @SerializedName("player")
    @Expose
    var playerList: List<Player> = emptyList()
}