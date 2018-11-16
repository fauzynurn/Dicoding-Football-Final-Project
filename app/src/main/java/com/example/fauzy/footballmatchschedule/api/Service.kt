package com.example.fauzy.footballmatchschedule.api

import com.example.fauzy.footballmatchschedule.models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {
    @GET("eventspastleague.php")
    fun getPastEvents(@Query("id") id : String): Observable<MatchList>

    @GET("eventsnextleague.php")
    fun getNextEvents(@Query("id") id : String): Observable<MatchList>

    @GET("lookupevent.php")
    fun getEventData(@Query("id") eventId: String): Observable<EventDetailList>

    @GET("lookup_all_teams.php?id=4328")
    fun getTeamDetail(): Observable<TeamDetailList>

    @GET("searchevents.php")
    fun searchMatch(@Query("e") query : String) : Observable<SearchEventList>

    @GET("search_all_teams.php")
    fun searchTeam(@Query("l") query : String) : Observable<TeamList>

    @GET("searchteams.php")
    fun searchTeamByName(@Query("t") query : String) : Observable<TeamList>

    @GET("lookup_all_players.php")
    fun getAllPlayers(@Query("id") query : String) : Observable<PlayerList>
}