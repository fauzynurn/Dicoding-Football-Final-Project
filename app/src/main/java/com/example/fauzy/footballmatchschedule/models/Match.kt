package com.example.fauzy.footballmatchschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Match {
    @SerializedName("idEvent")
    @Expose
    var idEvent: String = "ID"

    @SerializedName("idHomeTeam")
    @Expose
    var idHomeTeam: String? = null

    @SerializedName("idAwayTeam")
    @Expose
    var idAwayTeam: String? = null

    @SerializedName("strHomeTeam")
    @Expose
    var homeTeam: String? = null

    @SerializedName("strAwayTeam")
    @Expose
    var awayTeam: String? = null

    @SerializedName("intHomeScore")
    @Expose
    var homeScore: String? = null

    @SerializedName("intAwayScore")
    @Expose
    var awayScore: String? = null

    @SerializedName("dateEvent")
    @Expose
    var dateEvent: String? = null

    @SerializedName("strHomeGoalDetails")
    @Expose
    var homeGoalDetail: String? = null

    @SerializedName("intHomeShots")
    @Expose
    var homeShots: String? = null

    @SerializedName("strHomeLineupGoalkeeper")
    @Expose
    var homeGoalKeeper: String? = null

    @SerializedName("strHomeLineupDefense")
    @Expose
    var homeDefense: String? = null

    @SerializedName("strHomeLineupMidfield")
    @Expose
    var homeMidfield: String? = null

    @SerializedName("strHomeLineupForward")
    @Expose
    var homeForward: String? = null

    @SerializedName("strHomeLineupSubstitutes")
    @Expose
    var homeSubtitutes: String? = null

    @SerializedName("intAwayShots")
    @Expose
    var awayShots: String? = null

    @SerializedName("strAwayLineupGoalkeeper")
    @Expose
    var awayGoalKeeper: String? = null

    @SerializedName("strAwayLineupDefense")
    @Expose
    var awayDefense: String? = null

    @SerializedName("strAwayLineupMidfield")
    @Expose
    var awayMidfield: String? = null

    @SerializedName("strAwayLineupForward")
    @Expose
    var awayForward: String? = null

    @SerializedName("strAwayLineupSubstitutes")
    @Expose
    var awaySubtitutes: String? = null

    @SerializedName("strAwayGoalDetails")
    @Expose
    var awayGoalDetail: String? = null

    var homeTeamLogo: String? = null
    var awayTeamLogo: String? = null
}