package com.example.fauzy.footballmatchschedule.ui.fragments.detailteam.players

import com.example.fauzy.footballmatchschedule.models.PlayerList
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback

interface IViewPlayer : RepositoryCallback<PlayerList> {
    fun onShowLoading()
    fun onHideLoading()
}