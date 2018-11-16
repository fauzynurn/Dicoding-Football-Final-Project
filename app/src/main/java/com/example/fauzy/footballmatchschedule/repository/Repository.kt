package com.example.fauzy.footballmatchschedule.repository

import com.example.fauzy.footballmatchschedule.api.RetrofitClientInstance
import com.example.fauzy.footballmatchschedule.api.Service
import com.example.fauzy.footballmatchschedule.models.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import org.jetbrains.anko.support.v4.toast

class Repository {
    internal var service: Service? = RetrofitClientInstance.getInstance()?.create(
        Service::class.java
    )

    fun getPastEvents(id : String , callback: RepositoryCallback<MatchList>) {
        service?.getPastEvents(id)!!
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ matchResult ->
                matchResult.let {
                    it.eventList?.map { it -> it.dateEvent = formatDate(it.dateEvent) }
                    callback.onDataLoaded(it)
                }

            }, {
                callback.onDataError(it.message.toString())
            })
    }

    fun getNextEvents(id : String ,callback: RepositoryCallback<MatchList>) {
        service?.getNextEvents(id)!!
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ matchResult ->
                matchResult.let {
                    it.eventList?.map { it -> it.dateEvent = formatDate(it.dateEvent) }
                    callback.onDataLoaded(it)
                }

            }, {
                callback.onDataError(it.message.toString())
            })
    }

    fun getDetailMatch(id: String, callback: RepositoryCallback<DetailResult>) {
        Observable.zip(
            service?.getEventData(id),
            service?.getTeamDetail(),
            BiFunction { eventDetailList: EventDetailList, detailList: TeamDetailList ->
                DetailResult(eventDetailList, detailList)
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                it.let {
                    callback.onDataLoaded(it)
                }
            }, {
                callback.onDataError(it.message.toString())
            })
    }

    fun searchMatch(query : String, callback: RepositoryCallback<SearchEventList>){
        service?.searchMatch(query)!!
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ matchResult ->
                matchResult.let {
                    if(it.eventList == null){
                        callback.onDataError("Result not found")
                    }else {
                        it.eventList?.map { it -> it.dateEvent = formatDate(it.dateEvent) }
                        callback.onDataLoaded(it)
                    }
                }
            }, {
                callback.onDataError(it.message.toString())
            })
    }

    fun getTeams(query : String, callback: RepositoryCallback<TeamList>){
        service?.searchTeam(query)!!
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ matchResult ->
                matchResult.let {
                    if(it.teamList == null){
                        callback.onDataError("Result not found")
                    }else {
                        callback.onDataLoaded(it)
                    }
                }
            }, {
                callback.onDataError(it.message.toString())
            })
    }

    fun getTeamsByName(query : String, callback: RepositoryCallback<TeamList>){
        service?.searchTeamByName(query)!!
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ matchResult ->
                matchResult.let {
                    if(it.teamList == null){
                        callback.onDataError("Result not found")
                    }else {
                        callback.onDataLoaded(it)
                    }
                }
            }, {
                callback.onDataError(it.message.toString())
            })
    }

    fun getPlayers(id : String, callback: RepositoryCallback<PlayerList>){
        service?.getAllPlayers(id)!!
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ matchResult ->
                matchResult.let {
                    if(it.playerList == null){
                        callback.onDataError("Result not found")
                    }else {
                        callback.onDataLoaded(it)
                    }
                }
            }, {
                callback.onDataError(it.message.toString())
            })
    }

    fun formatDate(date: String?): String {
        var rawDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)
        var day = SimpleDateFormat("EEEE", Locale.ENGLISH).format(rawDate.time)
        var date = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(rawDate.time)
        return "$day, $date"
    }
}