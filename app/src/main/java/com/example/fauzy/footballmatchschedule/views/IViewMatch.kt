package com.example.fauzy.footballmatchschedule.views

import com.example.fauzy.footballmatchschedule.models.MatchList
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback

interface IViewMatch : RepositoryCallback<MatchList> {
    fun onShowLoading()
    fun onHideLoading()
}