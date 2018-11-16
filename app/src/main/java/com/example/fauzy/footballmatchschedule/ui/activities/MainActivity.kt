package com.example.fauzy.footballmatchschedule.ui.activities

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.*
import com.example.fauzy.footballmatchschedule.ui.activities.fragments.favourites.FavoritesFragment
import com.example.fauzy.footballmatchschedule.ui.activities.fragments.matches.MatchesFragment
import com.example.fauzy.footballmatchschedule.ui.activities.fragments.matches.MatchesPresenter
import com.example.fauzy.footballmatchschedule.ui.activities.fragments.teams.TeamsFragment


class MainActivity : AppCompatActivity() {
    lateinit var spinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.elevation = 0F
        bottom_navigation.setOnNavigationItemSelectedListener {
            item -> when(item.itemId){
            matches_item -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, MatchesFragment()
                            ,"MATCHES")
                        .commit()
            }
            teams_item -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container,
                            TeamsFragment()
                            , TeamsFragment::class.simpleName)
                        .commit()
            }
            favorite_item -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, FavoritesFragment()
                            , FavoritesFragment::class.simpleName)
                        .commit()
            }
        }
            true
        }
        bottom_navigation.selectedItemId = matches_item
    }
}