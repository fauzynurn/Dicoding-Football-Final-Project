package com.example.fauzy.footballmatchschedule.ui.activities.detailplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.models.Player
import kotlinx.android.synthetic.main.player_detail_layout.*

class DetailPlayerActivity : AppCompatActivity(){
    lateinit var playerObject : Player

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_detail_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        playerObject = intent.getSerializableExtra("playerObject") as Player
        supportActionBar?.title = playerObject.playerName
        Glide.with(this)
            .load(playerObject.fanArt)
            .into(thumbnail)
        weight.text = playerObject.playerWeight
        height.text = playerObject.playerHeight
        position_text.text = playerObject.playerPosition
        player_desc.text = playerObject.playerDesc
    }

}