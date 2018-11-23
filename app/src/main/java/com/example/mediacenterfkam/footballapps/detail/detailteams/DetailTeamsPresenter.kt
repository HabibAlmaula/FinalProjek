package com.example.mediacenterfkam.footballapps.detail.detailteams

import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.apidb.TheSportsDbApi
import com.example.mediacenterfkam.footballapps.response.PlayersResponse
import com.example.mediacenterfkam.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTeamsPresenter(private val mView: DetailTeamsView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    fun getPlayers(e: String?){
        mView.showLoading()

        GlobalScope.launch(context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportsDbApi.getPlayers(e!!)).await(),
                PlayersResponse::class.java)

            mView.hideLoading()

            try {
                mView.showAllPlayers(data.players)
            } catch (e: NullPointerException) {
                mView.showEmptydata()
            }
        }
    }

}