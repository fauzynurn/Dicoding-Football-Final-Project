package com.example.fauzy.footballmatchschedule.ui.activities.detailmatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.example.fauzy.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.example.fauzy.footballmatchschedule.R.id.add_to_favorite
import com.example.fauzy.footballmatchschedule.R.menu.menu_list
import com.example.fauzy.footballmatchschedule.api.database
import com.example.fauzy.footballmatchschedule.models.DetailResult
import com.example.fauzy.footballmatchschedule.models.Favorite
import com.example.fauzy.footballmatchschedule.models.Match
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.views.IViewDetail
import kotlinx.android.synthetic.main.match_detail_layout.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailMatchActivity : AppCompatActivity(), AnkoLogger, IViewDetail {
    private var menuItem: Menu? = null
    private var eventObject = Match()
    private var matchId = "ID"
    private var isFavorite: Boolean = false
    private var isLoading: Boolean = true
    private var matchObject: Match = Match()
    lateinit var detailPresenter: DetailMatchPresenter

    override fun onShowLoading() {
        isLoading = true
    }

    override fun onHideLoading() {
        isLoading = false
    }

    override fun onDataLoaded(data: DetailResult) {
        val circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
        eventObject = data.eventDetailList?.eventList?.get(0) as Match
        eventObject.homeTeamLogo =
                data.detailList?.teamList?.find { item -> item.idTeam == eventObject.idHomeTeam }?.logoLink
        eventObject.awayTeamLogo =
                data.detailList?.teamList?.find { item -> item.idTeam == eventObject.idAwayTeam }?.logoLink

        Glide.with(this)
            .load(eventObject.homeTeamLogo)
            .apply(RequestOptions().placeholder(circularProgressDrawable))
            .into(home_logo)

        Glide.with(this)
            .load(eventObject.awayTeamLogo)
            .apply(RequestOptions().placeholder(circularProgressDrawable))
            .into(away_logo)

        home_team_name.text = eventObject.homeTeam
        away_team_name.text = eventObject.awayTeam
        home_team_score.text = eventObject.homeScore
        away_team_score.text = eventObject.awayScore
        if (eventObject.homeScore == null) {
            separator.text = "vs"
        }
        match_date.text = intent.getStringExtra("reformattedDate")
        home_team_goal.text = eventObject.homeGoalDetail
        away_team_goal.text = eventObject.awayGoalDetail
        home_team_shot.text = eventObject.homeShots
        away_team_shot.text = eventObject.awayShots
        home_team_goalkeeper.text = eventObject.homeGoalKeeper
        away_team_goalkeeper.text = eventObject.awayGoalKeeper
        home_team_defense.text = eventObject.homeDefense
        away_team_defense.text = eventObject.awayDefense
        home_team_midfield.text = eventObject.homeMidfield
        away_team_midfield.text = eventObject.awayMidfield
        home_team_forward.text = eventObject.homeForward
        away_team_forward.text = eventObject.awayForward
        home_team_subtitutes.text = eventObject.homeSubtitutes
        away_team_subtitutes.text = eventObject.awaySubtitutes

        //variabel di bawah digunakan untuk menyimpan data pada sqlite
        matchObject.homeTeamLogo = eventObject.homeTeamLogo
        matchObject.awayTeamLogo = eventObject.awayTeamLogo
        matchObject.homeScore = eventObject.homeScore
        matchObject.awayScore = eventObject.awayScore
        matchObject.homeTeam = eventObject.homeTeam
        matchObject.awayTeam = eventObject.awayTeam
        matchObject.dateEvent = intent.getStringExtra("reformattedDate")
        matchObject.idEvent = eventObject.idEvent
    }

    override fun onDataError(data : String) {
        toast(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_list, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isLoading) {
                    toast("Application is still loading the data. Please wait..")
                } else {
                    if (isFavorite) removeFromFavorite() else addToFavorite()
                    isFavorite = !isFavorite
                    setFavorite()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_detail_layout)
        matchId = intent.getStringExtra("matchId")
        favoriteState()
        supportActionBar?.elevation = 0F
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailPresenter = DetailMatchPresenter(
            this,
            Repository()
        )
        detailPresenter.getDetailMatches(matchId)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    "TABLE_FAVORITE",
                    Favorite.EVENT_ID to matchObject.idEvent,
                    Favorite.EVENT_DATE to matchObject.dateEvent,
                    Favorite.HOME_TEAM_NAME to matchObject.homeTeam,
                    Favorite.AWAY_TEAM_NAME to matchObject.awayTeam,
                    "HOME_TEAM_SCORE" to matchObject.homeScore,
                    "AWAY_TEAM_SCORE" to matchObject.awayScore
                )
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete("TABLE_FAVORITE", "(EVENT_ID = {id})", "id" to matchId)
            }
            toast("Removed from favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select("TABLE_FAVORITE")
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to matchId
                )
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}