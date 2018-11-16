package com.example.fauzy.footballmatchschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TeamList{
    @SerializedName("teams")
    @Expose
    var teamList: List<Team> = emptyList()
}