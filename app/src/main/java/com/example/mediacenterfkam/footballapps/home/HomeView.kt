package com.example.mediacenterfkam.footballapps.home

import com.example.mediacenterfkam.footballapps.response.LeagueResponse

interface HomeView{
    fun showLeagueList(data: LeagueResponse)

}