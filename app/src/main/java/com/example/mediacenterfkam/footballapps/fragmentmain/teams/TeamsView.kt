package com.example.mediacenterfkam.footballapps.fragmentmain.teams

import com.example.mediacenterfkam.footballapps.response.TeamsItem

interface TeamsView{
    fun showLoading()
    fun hideLoading()
    fun showEmptyData()
    fun showTeams (data: List<TeamsItem>?)
}