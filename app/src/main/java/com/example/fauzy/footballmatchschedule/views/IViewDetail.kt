package com.example.fauzy.footballmatchschedule.views

import com.example.fauzy.footballmatchschedule.models.DetailResult
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback

interface IViewDetail : RepositoryCallback<DetailResult> {
    fun onShowLoading()
    fun onHideLoading()
}