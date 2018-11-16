package com.example.fauzy.footballmatchschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TeamDetailList {
    @SerializedName("teams")
    @Expose
    var teamList: List<TeamDetail>? = null
}