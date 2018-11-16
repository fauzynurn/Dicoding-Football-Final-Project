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
import com.example.fauzy.footballmatchschedule.models.Player

class PlayerAdapter(
    private val context: Context?,
    private val items: List<Player>,
    private val listener: (Player) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.player_item_layout,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val playerImage = view.findViewById<ImageView>(R.id.player_pic)
        private val playerName = view.findViewById<TextView>(R.id.player_name)
        private val playerPosition = view.findViewById<TextView>(R.id.position)

        fun bindItem(items: Player, listener: (Player) -> Unit) {
            Glide.with(itemView.context)
                .load(items.playerImage)
                .into(playerImage)
            playerName.text = items.playerName
            playerPosition.text = items.playerPosition
            itemView.setOnClickListener {
                listener(items)
            }
        }
    }
}