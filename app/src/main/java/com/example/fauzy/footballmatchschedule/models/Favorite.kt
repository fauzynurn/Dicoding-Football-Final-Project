package com.example.fauzy.footballmatchschedule.models

data class Favorite(
    val EVENT_ID: String,
    val HOME_TEAM_NAME: String?,
    val AWAY_TEAM_NAME: String?,
    val AWAY_TEAM_SCORE: String?,
    val HOME_TEAM_SCORE: String?,
    val EVENT_DATE: String?
) {

    companion object {
        const val EVENT_ID: String = "EVENT_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        val AWAY_TEAM_SCORE: String? = "AWAY_TEAM_SCORE"
        val HOME_TEAM_SCORE: String? = "HOME_TEAM_SCORE"
        const val EVENT_DATE: String = "EVENT_DATE"
    }
}