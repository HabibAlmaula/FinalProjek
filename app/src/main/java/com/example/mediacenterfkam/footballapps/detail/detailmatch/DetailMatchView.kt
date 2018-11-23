package com.example.mediacenterfkam.footballapps.detail.detailmatch

import com.example.mediacenterfkam.footballapps.response.TeamsItem

interface DetailMatchView{
    fun showLoading()
    fun hideLoading()
    fun showTeamDetails(dataHomeTeam: List<TeamsItem>?, dataAwayTeam: List<TeamsItem>?)
}