package com.example.fauzy.footballmatchschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MatchList {
    @SerializedName("events")
    @Expose
    lateinit var eventList: List<Match>
}