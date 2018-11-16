package com.example.fauzy.footballmatchschedule.ui.activities.searchteam

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.adapters.TeamAdapter
import com.example.fauzy.footballmatchschedule.models.Team
import com.example.fauzy.footballmatchschedule.models.TeamList
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.ui.activities.detailteam.DetailTeamActivity
import kotlinx.android.synthetic.main.search_team_layout.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.toast

class SearchTeamActivity : AppCompatActivity(), IViewSearchTeam{
    lateinit var presenter : SearchTeamPresenter
    lateinit var teamList: List<Team>
    override fun onShowLoading() {
        search_team_prog_bar.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        search_team_prog_bar.visibility = View.GONE
    }

    override fun onDataLoaded(data: TeamList) {
        teamList = data.teamList
        search_teams_list?.adapter = TeamAdapter(
            applicationContext,
            teamList
        ) {
            startActivity(
                intentFor<DetailTeamActivity>(
                    "teamObject" to it
                )
            )
        }
    }

    override fun onDataError(message: String) {
        toast(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_team_layout)
        search_teams_list?.layoutManager = LinearLayoutManager(this)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_search_team_layout)
        presenter = SearchTeamPresenter(this, Repository())
        val view = supportActionBar?.customView
        val searchBar = view?.findViewById<SearchView>(R.id.search_team_view)
        searchBar?.maxWidth = Integer.MAX_VALUE
        searchBar?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                onShowLoading()
                presenter.getTeams(p0)
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                return false
            }
        }
        )
    }

}