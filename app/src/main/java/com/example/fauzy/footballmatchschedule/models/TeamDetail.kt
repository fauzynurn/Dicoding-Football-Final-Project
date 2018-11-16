package com.example.fauzy.footballmatchschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TeamDetail {
    @SerializedName("idTeam")
    @Expose
    var idTeam: String? = null

    @SerializedName("strTeamBadge")
    @Expose
    var logoLink: String? = null
}