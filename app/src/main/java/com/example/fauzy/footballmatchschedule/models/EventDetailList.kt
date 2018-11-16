package com.example.fauzy.footballmatchschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventDetailList {
    @SerializedName("events")
    @Expose
    var eventList: List<Match>? = null
}