package com.example.fauzy.footballmatchschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Player : Serializable{
    @SerializedName("strPlayer")
    @Expose
    var playerName: String? = null

    @SerializedName("strDescriptionEN")
    @Expose
    var playerDesc: String? = null

    @SerializedName("strPosition")
    @Expose
    var playerPosition: String? = null

    @SerializedName("strHeight")
    @Expose
    var playerHeight: String? = null

    @SerializedName("strWeight")
    @Expose
    var playerWeight: String? = null

    @SerializedName("strCutout")
    @Expose
    var playerImage: String? = null

    @SerializedName("strFanart1")
    @Expose
    var fanArt: String? = null
}