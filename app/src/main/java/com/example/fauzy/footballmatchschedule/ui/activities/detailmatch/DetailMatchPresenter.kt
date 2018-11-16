package com.example.fauzy.footballmatchschedule.ui.activities.detailmatch

import com.example.fauzy.footballmatchschedule.models.DetailResult
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback
import com.example.fauzy.footballmatchschedule.views.IViewDetail

class DetailMatchPresenter(private val viewMatch: IViewDetail, private val repo: Repository) {
    fun getDetailMatches(id: String) {
        viewMatch.onShowLoading()
        repo.getDetailMatch(id, object : RepositoryCallback<DetailResult> {
            override fun onDataLoaded(data: DetailResult) {
                viewMatch.onHideLoading()
                viewMatch.onDataLoaded(data)
            }

            override fun onDataError(data : String) {
                viewMatch.onDataError(data)
            }
        })
    }
}