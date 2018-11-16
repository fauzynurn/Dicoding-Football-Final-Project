package com.example.fauzy.footballmatchschedule.ui.fragments.teams

import com.example.fauzy.footballmatchschedule.models.TeamList
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback

interface IViewTeams : RepositoryCallback<TeamList> {
    fun onShowLoading()
    fun onHideLoading()
}