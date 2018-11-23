package com.example.mediacenterfkam.footballapps.home

import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.apidb.TheSportsDbApi
import com.example.mediacenterfkam.footballapps.response.LeagueResponse
import com.example.mediacenterfkam.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePresenter(
    private val mView: HomeView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context : CoroutineContextProvider = CoroutineContextProvider()
){

    fun getAllLeague(){

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportsDbApi.getAllLeague()).await(),
                LeagueResponse::class.java)

            mView.showLeagueList(data)
        }
    }


}