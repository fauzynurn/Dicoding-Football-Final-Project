package com.example.fauzy.footballmatchschedule.ui.activities.fragments.favourites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.adapters.FavoriteListAdapter
import com.example.fauzy.footballmatchschedule.api.database
import com.example.fauzy.footballmatchschedule.models.Favorite
import com.example.fauzy.footballmatchschedule.ui.activities.detailmatch.DetailMatchActivity
import kotlinx.android.synthetic.main.favourite_layout.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class FavoritesFragment : Fragment() {
    private var favorites: MutableList<Favorite> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipe_refresh.onRefresh {
            favorites.clear()
            showFavorite()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fav_list?.layoutManager = LinearLayoutManager(activity)
        fav_list?.adapter = FavoriteListAdapter(
            activity?.applicationContext,
            favorites
        ) {
            startActivity(intentFor<DetailMatchActivity>("matchId" to it.EVENT_ID, "reformattedDate" to it.EVENT_DATE))
        }
        showFavorite()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.favourite_layout, container, false)
    }

    private fun showFavorite() {
        context?.database?.use {
            val result = select("TABLE_FAVORITE")
            val favorite = result.parseList(classParser<Favorite>())
            favorites.clear()
            favorites.addAll(favorite)
            fav_list?.adapter?.notifyDataSetChanged()
        }
        swipe_refresh.isRefreshing = false
    }
}