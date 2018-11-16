package com.example.fauzy.footballmatchschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Team : Serializable{
    @SerializedName("idTeam")
    @Expose
    var idTeam: String = "ID"

    @SerializedName("strTeam")
    @Expose
    var teamName: String? = null

    @SerializedName("intFormedYear")
    @Expose
    var formedYear: String? = null

    @SerializedName("strStadium")
    @Expose
    var stadium: String? = null

    @SerializedName("strDescriptionEN")
    @Expose
    var desc: String? = null

    @SerializedName("strTeamBadge")
    @Expose
    var badge: String? = null
}