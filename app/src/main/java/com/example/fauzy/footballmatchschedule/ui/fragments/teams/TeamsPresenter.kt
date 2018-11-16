package com.example.fauzy.footballmatchschedule.ui.fragments.teams

import com.example.fauzy.footballmatchschedule.models.TeamList
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback

class TeamsPresenter(private val viewMatch: IViewTeams, private val repo: Repository) {
    fun getTeams(teamName : String) {
        viewMatch.onShowLoading()
            repo.getTeams(teamName,object : RepositoryCallback<TeamList> {
                override fun onDataLoaded(data: TeamList) {
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