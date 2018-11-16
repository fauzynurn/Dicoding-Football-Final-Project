package com.example.fauzy.footballmatchschedule.ui.fragments.detailteam.players

import com.example.fauzy.footballmatchschedule.models.PlayerList
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback

class PlayersPresenter(private val viewMatch: IViewPlayer, private val repo: Repository) {
    fun getPlayers(id : String) {
        viewMatch.onShowLoading()
            repo.getPlayers(id,object : RepositoryCallback<PlayerList> {
                override fun onDataLoaded(data: PlayerList) {
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