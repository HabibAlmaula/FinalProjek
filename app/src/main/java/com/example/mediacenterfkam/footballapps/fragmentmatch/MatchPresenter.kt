package com.example.mediacenterfkam.footballapps.fragmentmatch

import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.apidb.TheSportsDbApi
import com.example.mediacenterfkam.footballapps.response.MatchResponse
import com.example.mediacenterfkam.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter(private val mView: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context : CoroutineContextProvider = CoroutineContextProvider()) {

    fun getCurrentMatch(id: String) {
        mView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportsDbApi.getCurrentLeague(id)).await(),
                MatchResponse::class.java
            )

            mView.hideLoading()

            try {
                mView.showMatchList(data.events)
            } catch (e: NullPointerException) {
                mView.showEmptyData()
            }
        }
    }

    fun getEventsNext(id: String) {
        mView.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportsDbApi.getNextLeague(id)).await(),
                MatchResponse::class.java
            )
            mView.hideLoading()

            try {
                mView.showMatchList(data.events)
            } catch (e: NullPointerException) {
                mView.showEmptyData()
            }


        }

    }

    fun getSearchMatch(name: String?) {
        mView.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                name?.let { TheSportsDbApi.getSearchMatch(it) }?.let {
                    apiRepository
                        .doRequest(it).await()
                },
                MatchResponse::class.java
            )

            mView.hideLoading()

            try {
                mView.showMatchList(data.events)
            } catch (e: NullPointerException) {
                mView.showEmptyData()
            }
        }
    }
}