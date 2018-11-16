package com.example.fauzy.footballmatchschedule.ui.activities.fragments.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.R.id.search_team_icon
import com.example.fauzy.footballmatchschedule.R.menu.teams_menu_item
import com.example.fauzy.footballmatchschedule.adapters.TeamAdapter
import com.example.fauzy.footballmatchschedule.models.Team
import com.example.fauzy.footballmatchschedule.models.TeamList
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.ui.activities.detailteam.DetailTeamActivity
import com.example.fauzy.footballmatchschedule.ui.activities.searchteam.SearchTeamActivity
import com.example.fauzy.footballmatchschedule.ui.fragments.teams.IViewTeams
import com.example.fauzy.footballmatchschedule.ui.fragments.teams.TeamsPresenter
import kotlinx.android.synthetic.main.teams_layout.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast

class TeamsFragment : Fragment(), IViewTeams{
    lateinit var presenter : TeamsPresenter
    lateinit var spinner : Spinner
    lateinit var teamList : List<Team>

    override fun onShowLoading() {
        team_prog_bar.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        team_prog_bar.visibility = View.GONE
    }

    override fun onDataLoaded(data: TeamList) {
        teamList = data.teamList
        team_list.adapter = TeamAdapter(context,teamList) {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        team_list?.layoutManager = LinearLayoutManager(activity)
        presenter = TeamsPresenter(this, Repository())
        team_spinner.adapter = ArrayAdapter(context, android.support.design.R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.league))
        team_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getTeams(team_spinner.selectedItem.toString())
            }

        }
        presenter.getTeams("English Premier League")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            search_team_icon -> {
                startActivity(
                    intentFor<SearchTeamActivity>()
                )
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(teams_menu_item, menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.teams_layout, container, false)
    }
}