package com.example.fauzy.footballmatchschedule.ui.activities.detailteam

import android.os.Bundle
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.R.id.add_to_favorite
import com.example.fauzy.footballmatchschedule.R.menu.menu_list
import com.example.fauzy.footballmatchschedule.adapters.TeamDetailFragmentAdapter
import com.example.fauzy.footballmatchschedule.models.Team
import com.example.fauzy.footballmatchschedule.ui.fragments.detailteam.overview.OverviewFragment
import com.example.fauzy.footballmatchschedule.ui.fragments.detailteam.players.PlayersFragment
import kotlinx.android.synthetic.main.team_detail_layout.*
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(){
    lateinit var teamObject : Team
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                toast("Favorite clicked!!")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        setContentView(R.layout.team_detail_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.elevation = 0F
        teamObject = intent.getSerializableExtra("teamObject") as Team
        val bundle = Bundle()
        val adapter = TeamDetailFragmentAdapter(supportFragmentManager)
        bundle.putSerializable("teamObject", teamObject)
        val overviewFragment =
            OverviewFragment()
        val playersFragment = PlayersFragment()
        overviewFragment.arguments = bundle
        playersFragment.arguments = bundle
        adapter.addFragment(overviewFragment,"Overview")
        adapter.addFragment(playersFragment,"Players")
        teams_detail_pager.adapter = adapter
        tab_layout.setupWithViewPager(teams_detail_pager)
        Glide.with(this)
            .load(teamObject.badge)
            .apply(RequestOptions().placeholder(circularProgressDrawable))
            .into(badge_detail_logo)

        year.text = teamObject.formedYear
        stadium.text = teamObject.stadium

    }

}