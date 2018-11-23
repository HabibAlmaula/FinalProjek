package com.example.mediacenterfkam.footballapps.home

import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.apidb.TheSportsDbApi
import com.example.mediacenterfkam.footballapps.response.LeagueResponse
import com.example.mediacenterfkam.footballapps.response.LeaguesItem
import com.example.mediacenterfkam.footballapps.utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomePresenterTest {
    @Mock
    lateinit var mView: HomeView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Mock
    lateinit var presenter: HomePresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = HomePresenter(mView, apiRepository,gson,
            TestContextProvider()
        )

    }

    @Test
    fun getAllLeague() {
        val league: MutableList<LeaguesItem> = mutableListOf()
        val response = LeagueResponse(league)

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportsDbApi.getAllLeague()).await(),
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getAllLeague()

            Mockito.verify(mView).showLeagueList(response)
        }
    }
}