package com.example.mediacenterfkam.footballapps.detail

import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.apidb.TheSportsDbApi
import com.example.mediacenterfkam.footballapps.detail.detailmatch.DetailMatchPresenter
import com.example.mediacenterfkam.footballapps.detail.detailmatch.DetailMatchView
import com.example.mediacenterfkam.footballapps.response.TeamsItem
import com.example.mediacenterfkam.footballapps.response.TeamsResponse
import com.example.mediacenterfkam.footballapps.utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {

    @Mock
    lateinit var mView: DetailMatchView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Mock
    lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(
            mView,
            apiRepository,
            gson,
            TestContextProvider()
        )
    }

    @Test
    fun getTeamDetails() {

        val team : MutableList<TeamsItem> = mutableListOf()
        val response = TeamsResponse(team)

        val id = "2345"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportsDbApi.getTeams(id)).await(),
                TeamsResponse::class.java)).thenReturn(response)

            presenter.getTeamDetails(id, id)

            Mockito.verify(mView).showLoading()
            Mockito.verify(mView).showTeamDetails(response.teams,response.teams)
            Mockito.verify(mView).hideLoading()
        }
    }
}