package com.example.fauzy.footballmatchschedule.ui.activities.fragments.matches

import android.provider.Settings.Global.getString
import com.example.fauzy.footballmatchschedule.models.MatchList
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback
import com.example.fauzy.footballmatchschedule.views.IViewMatch

class MatchesPresenter(private val viewMatch: IViewMatch, private val repo: Repository) {
    fun getMatches(leagueName : String, matchMode : String) {
        viewMatch.onShowLoading()
        if(matchMode == "Last matches"){
            repo.getPastEvents(leagueName,object : RepositoryCallback<MatchList> {
                override fun onDataLoaded(data: MatchList) {
                    viewMatch.onDataLoaded(data)
                    viewMatch.onHideLoading()
                }

                override fun onDataError(data : String) {
                    viewMatch.onDataError(data)
                    viewMatch.onHideLoading()
                }
            })
        }else{
            repo.getNextEvents(leagueName,object : RepositoryCallback<MatchList> {
                override fun onDataLoaded(data: MatchList) {
                    viewMatch.onDataLoaded(data)
                    viewMatch.onHideLoading()
                }

                override fun onDataError(data : String) {
                    viewMatch.onDataError(data)
                    viewMatch.onHideLoading()
                }
            })
        }
    }
}