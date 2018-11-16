package com.example.fauzy.footballmatchschedule.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.models.Favorite

class FavoriteListAdapter(
    private val context: Context?,
    private val items: List<Favorite>,
    private val listener: (Favorite) -> Unit
) : RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.match_item_layout,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val match_date = view.findViewById<TextView>(R.id.match_date)
        private val home_team_name = view.findViewById<TextView>(R.id.home_team_name)
        private val away_team_name = view.findViewById<TextView>(R.id.away_team_name)
        private val home_team_score = view.findViewById<TextView>(R.id.home_team_score)
        private val away_team_score = view.findViewById<TextView>(R.id.away_team_score)
        private val separator = view.findViewById<TextView>(R.id.separator)

        fun bindItem(items: Favorite, listener: (Favorite) -> Unit) {
            home_team_name.text = items.HOME_TEAM_NAME
            away_team_name.text = items.AWAY_TEAM_NAME
            //hanya perlu mengecek salah satu score saja
            if (items.HOME_TEAM_SCORE == null) {
                home_team_score.visibility = View.INVISIBLE
                away_team_score.visibility = View.INVISIBLE
                separator.text = "vs"
            } else {
                home_team_score.text = items.HOME_TEAM_SCORE
                away_team_score.text = items.AWAY_TEAM_SCORE
            }
            match_date.text = items.EVENT_DATE
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}