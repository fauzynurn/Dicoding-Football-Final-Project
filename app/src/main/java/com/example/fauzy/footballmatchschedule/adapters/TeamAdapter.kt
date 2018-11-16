package com.example.fauzy.footballmatchschedule.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.models.Team

class TeamAdapter(
    private val context: Context?,
    private val items: List<Team>,
    private val listener: (Team) -> Unit
) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.team_item_layout,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val teamBadge = view.findViewById<ImageView>(R.id.team_badge)
        private val teamName = view.findViewById<TextView>(R.id.team_name)

        fun bindItem(items: Team, listener: (Team) -> Unit) {
            Glide.with(itemView.context)
                .load(items.badge)
                .into(teamBadge)
            teamName.text = items.teamName
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}