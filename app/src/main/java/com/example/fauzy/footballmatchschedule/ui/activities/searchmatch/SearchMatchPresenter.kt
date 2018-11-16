package com.example.fauzy.footballmatchschedule.ui.activities.searchmatch

import com.example.fauzy.footballmatchschedule.models.SearchEventList
import com.example.fauzy.footballmatchschedule.repository.Repository
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback

class SearchMatchPresenter(private val viewMatch: IViewSearchMatch, private val repo: Repository) {
    fun getMatches(query: String) {
        viewMatch.onShowLoading()
            repo.searchMatch(query, object : RepositoryCallback<SearchEventList> {
                override fun onDataLoaded(data: SearchEventList) {
                    viewMatch.onDataLoaded(data)
                    viewMatch.onHideLoading()
                }

                override fun onDataError(data: String) {
                    viewMatch.onDataError(data)
                    viewMatch.onHideLoading()
                }
            })
        }
    }