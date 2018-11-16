package com.example.fauzy.footballmatchschedule.ui.fragments.detailteam.players

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.adapters.PlayerAdapter
import com.example.fauzy.footballmatchschedule.models.Player
import com.example.fauzy.footballmatchschedule.models.PlayerList
import com.example.fauzy.footballmatchschedule.models.Team
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.ui.activities.detailplayer.DetailPlayerActivity
import kotlinx.android.synthetic.main.players_layout.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

class PlayersFragment : Fragment(), IViewPlayer{
    lateinit var presenter : PlayersPresenter
    lateinit var playerList : List<Player>
    override fun onShowLoading() {
        player_prog_bar.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        player_prog_bar.visibility = View.INVISIBLE
    }

    override fun onDataLoaded(data: PlayerList) {
        playerList = data.playerList
        player_list.adapter = PlayerAdapter(activity?.applicationContext,playerList){
            startActivity(
                intentFor<DetailPlayerActivity>(
                    "playerObject" to it
                )
            )
        }
    }

    override fun onDataError(message: String) {
        toast(message)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        player_list.layoutManager = LinearLayoutManager(activity)
        presenter = PlayersPresenter(this, Repository())
        val teamObject = arguments?.getSerializable("teamObject") as Team
        presenter.getPlayers(teamObject.idTeam)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.players_layout, container, false)
    }



}