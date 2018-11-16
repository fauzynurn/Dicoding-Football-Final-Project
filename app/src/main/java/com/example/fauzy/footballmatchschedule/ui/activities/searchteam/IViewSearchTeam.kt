package com.example.fauzy.footballmatchschedule.ui.activities.searchteam

import com.example.fauzy.footballmatchschedule.models.TeamList
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback

interface IViewSearchTeam : RepositoryCallback<TeamList> {
    fun onShowLoading()
    fun onHideLoading()
}