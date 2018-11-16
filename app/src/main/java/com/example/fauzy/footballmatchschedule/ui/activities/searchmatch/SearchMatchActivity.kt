package com.example.fauzy.footballmatchschedule.ui.activities.searchmatch

import android.app.ActionBar
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.adapters.MatchListAdapter
import com.example.fauzy.footballmatchschedule.models.Match
import com.example.fauzy.footballmatchschedule.models.SearchEventList
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.ui.activities.detailmatch.DetailMatchActivity
import kotlinx.android.synthetic.main.search_match_layout.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class SearchMatchActivity : AppCompatActivity(), IViewSearchMatch{
    lateinit var presenter : SearchMatchPresenter
    lateinit var matchList: List<Match>
    lateinit var handler : Handler
    override fun onShowLoading() {
        search_prog_bar.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        search_prog_bar.visibility = View.GONE
    }

    override fun onDataLoaded(data: SearchEventList) {
        matchList = data.eventList
        search_matches_list?.adapter = MatchListAdapter(
            applicationContext,
            matchList
        ) {
            startActivity(
                intentFor<DetailMatchActivity>(
                    "matchId" to it.idEvent,
                    "reformattedDate" to matchList?.find { item -> item.idEvent == it.idEvent }?.dateEvent
                )
            )
        }
    }

    override fun onDataError(message: String) {
        toast(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_match_layout)
        search_matches_list?.layoutManager = LinearLayoutManager(this)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_search_match_layout)
        presenter = SearchMatchPresenter(this, Repository())
        val view = supportActionBar?.customView
        val searchBar = view?.findViewById<SearchView>(R.id.search_view)
        searchBar?.maxWidth = Integer.MAX_VALUE
        searchBar?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                onShowLoading()
                presenter.getMatches(p0)
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                return false
            }
            }
            )
    }

}