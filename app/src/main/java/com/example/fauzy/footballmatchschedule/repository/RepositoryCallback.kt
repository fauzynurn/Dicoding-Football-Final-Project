package com.example.fauzy.footballmatchschedule.repository

interface RepositoryCallback<T> {
    fun onDataLoaded(data: T)
    fun onDataError(message: String)
}