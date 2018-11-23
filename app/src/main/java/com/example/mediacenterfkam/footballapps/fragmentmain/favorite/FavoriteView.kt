package com.example.mediacenterfkam.footballapps.fragmentmain.favorite

import com.example.mediacenterfkam.footballapps.response.MatchItem

interface FavoriteView{
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showMatchListFavorite(data: List<MatchItem>)
}