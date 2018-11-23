package com.example.mediacenterfkam.footballapps.fragmentmain.teams

import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.apidb.TheSportsDbApi
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

class TeamsPresenterTest {
    @Mock
    lateinit var mView: TeamsView

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Mock
    lateinit var presenter: TeamsPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(mView,apiRepository,gson,
            TestContextProvider()
        )
    }


    @Test
    fun getTeams() {
        val teams : MutableList<TeamsItem> = mutableListOf()
        val response = TeamsResponse(teams)
        val id = "2345"

        GlobalScope.launch {
            Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TheSportsDbApi.getTeams(id)).await(),
                TeamsResponse::class.java)).thenReturn(response)

            presenter.getTeams(id)

            Mockito.verify(mView).showLoading()
            Mockito.verify(mView).showTeams(teams)
            Mockito.verify(mView).hideLoading()
        }
    }
}