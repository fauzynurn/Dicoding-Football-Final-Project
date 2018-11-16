package com.example.fauzy.footballmatchschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchEventList{
    @SerializedName("event")
    @Expose
    var eventList: List<Match> = emptyList()
}