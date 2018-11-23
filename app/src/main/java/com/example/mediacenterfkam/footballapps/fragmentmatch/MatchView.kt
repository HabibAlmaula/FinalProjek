package com.example.mediacenterfkam.footballapps.fragmentmatch

import com.example.mediacenterfkam.footballapps.response.MatchItem

interface MatchView{
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showMatchList(data: List<MatchItem>?)
}