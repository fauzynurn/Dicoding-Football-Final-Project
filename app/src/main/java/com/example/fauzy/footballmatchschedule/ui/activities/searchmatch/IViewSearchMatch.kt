package com.example.fauzy.footballmatchschedule.ui.activities.searchmatch

import com.example.fauzy.footballmatchschedule.models.SearchEventList
import com.example.fauzy.footballmatchschedule.repository.RepositoryCallback

interface IViewSearchMatch : RepositoryCallback<SearchEventList> {
    fun onShowLoading()
    fun onHideLoading()
}