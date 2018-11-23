package com.example.mediacenterfkam.footballapps.fragmentmatch

import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.apidb.TheSportsDbApi
import com.example.mediacenterfkam.footballapps.response.MatchItem
import com.example.mediacenterfkam.footballapps.response.MatchResponse
import com.example.mediacenterfkam.footballapps.utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    lateinit var mView : MatchView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Mock
    lateinit var presenter: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(mView,apiRepository,gson,
            TestContextProvider()
        )
    }


    @Test
    fun getCurrentMatch() {
        val events : MutableList<MatchItem> = mutableListOf()
        val response = MatchResponse(events)
        val id = "2345"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportsDbApi.getCurrentLeague(id)).await(),
                MatchResponse::class.java)).thenReturn(response)

            presenter.getCurrentMatch(id)

            Mockito.verify(mView).showLoading()
            Mockito.verify(mView).showMatchList(events)
            Mockito.verify(mView).hideLoading()
        }

    }

    @Test
    fun getEventsNext() {
        val events : MutableList<MatchItem> = mutableListOf()
        val response = MatchResponse(events)
        val id = "2345"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportsDbApi.getNextLeague(id)).await(),
                MatchResponse::class.java)).thenReturn(response)

            presenter.getEventsNext(id)

            Mockito.verify(mView).showLoading()
            Mockito.verify(mView).showMatchList(events)
            Mockito.verify(mView).hideLoading()        }
    }
}