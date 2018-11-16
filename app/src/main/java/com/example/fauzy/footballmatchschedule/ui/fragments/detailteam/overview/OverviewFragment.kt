package com.example.fauzy.footballmatchschedule.ui.fragments.detailteam.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fauzy.footballmatchschedule.R
import com.example.fauzy.footballmatchschedule.models.Team
import kotlinx.android.synthetic.main.overview_layout.*

class OverviewFragment : Fragment(){
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val teamObject = arguments?.getSerializable("teamObject") as Team
        overview.text = teamObject.desc
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.overview_layout, container, false)
    }



}