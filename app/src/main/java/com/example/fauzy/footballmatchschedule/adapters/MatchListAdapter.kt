package com.example.fauzy.footballmatchschedule.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.models.Match

class MatchListAdapter(
    private val context: Context?,
    private val items: List<Match>,
    private val listener: (Match) -> Unit
) : RecyclerView.Adapter<MatchListAdapter.ViewHolder>() {

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

        fun bindItem(items: Match, listener: (Match) -> Unit) {
            home_team_name.text = items.homeTeam
            away_team_name.text = items.awayTeam
            //hanya perlu mengecek salah satu score saja
            if (items.homeScore == null) {
                home_team_score.visibility = View.INVISIBLE
                away_team_score.visibility = View.INVISIBLE
                separator.text = "vs"
            } else {
                home_team_score.text = items.homeScore
                away_team_score.text = items.awayScore
            }
//            var rawDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(items.dateEvent)
//            var day = SimpleDateFormat("EEEE", Locale.ENGLISH).format(rawDate.time)
//            var date = SimpleDateFormat("dd-MM-yyyy",Locale.ENGLISH).format(rawDate.time)
            match_date.text = items.dateEvent
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}