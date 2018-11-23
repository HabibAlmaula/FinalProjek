package com.example.mediacenterfkam.footballapps.detail.detailteams

import com.example.mediacenterfkam.footballapps.response.PlayersItem
import com.example.mediacenterfkam.footballapps.response.PlayersResponse

interface DetailTeamsView{
    fun showLoading()
    fun hideLoading()
    fun showEmptydata()
    fun showAllPlayers(data : List<PlayersItem>?)
}