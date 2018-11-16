package com.example.fauzy.footballmatchschedule.ui.activities.searchteam

import com.example.fauzy.footballmatchschedule.models.TeamList
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback

class SearchTeamPresenter(private val viewMatch: IViewSearchTeam, private val repo: Repository) {
    fun getTeams(query: String) {
        viewMatch.onShowLoading()
        repo.getTeamsByName(query, object : RepositoryCallback<TeamList> {
            override fun onDataLoaded(data: TeamList) {
                viewMatch.onDataLoaded(data)
                viewMatch.onHideLoading()
            }

            override fun onDataError(data: String) {
                viewMatch.onDataError(data)
                viewMatch.onHideLoading()
            }
        })
    }
}