package com.example.mediacenterfkam.footballapps.fragmentmain.teams

import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.apidb.TheSportsDbApi
import com.example.mediacenterfkam.footballapps.response.TeamsResponse
import com.example.mediacenterfkam.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(private val mView : TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()){


    fun getTeams(id: String?){
        mView.showLoading()
        GlobalScope.launch(context.main){
            val data = gson.fromJson(
                id?.let { TheSportsDbApi.getTeams(it) }?.let { apiRepository.doRequest(it).await() },
                TeamsResponse::class.java)

            mView.hideLoading()


            try {
                mView.showTeams(data.teams)
            } catch (e: NullPointerException) {
                mView.showEmptyData()
            }

        }

    }

    fun getTeamSearch(id : String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                id?.let { TheSportsDbApi.getSearchTeam(it) }?.let { apiRepository.doRequest(it).await() },
                TeamsResponse::class.java)

            try {
                mView.showTeams(data.teams)
            } catch (e: NullPointerException) {
                mView.showEmptyData()
            }

        }
    }
}