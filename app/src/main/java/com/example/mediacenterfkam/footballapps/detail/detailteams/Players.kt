package com.example.mediacenterfkam.footballapps.detail.detailteams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mediacenterfkam.footballapps.R
import com.example.mediacenterfkam.footballapps.adapter.PlayersAdapter
import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.response.PlayersItem
import com.example.mediacenterfkam.footballapps.response.PlayersResponse
import com.example.mediacenterfkam.footballapps.response.TeamsItem
import com.example.mediacenterfkam.footballapps.utils.invisible
import com.example.mediacenterfkam.footballapps.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main_teams.*
import kotlinx.android.synthetic.main.fragment_players.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast


class Players : Fragment(), DetailTeamsView {
    private lateinit var presenter: DetailTeamsPresenter
    private var dataPlayers : MutableList<PlayersItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = DetailTeamsPresenter(this, ApiRepository(), Gson())

        val teamData : TeamsItem? = arguments?.getParcelable("TEAMSS")
        toast(teamData?.idTeam.toString())

        teamData?.idTeam?.let { presenter.getPlayers(it) }

        swipePlayers.onRefresh {
            teamData?.idTeam?.let { presenter.getPlayers(it) }
            progressplayers.invisible()
        }
    }

    override fun showLoading() {
        progressplayers.visible()
        rv_player.invisible()
        noDataplayers.invisible()
        Log.d("showLoading", "masuk")
    }

    override fun hideLoading() {
        progressplayers.invisible()
        rv_player.visible()
        noDataplayers.invisible()
        Log.d("hideloading","masukk")
    }

    override fun showEmptydata() {
        progressplayers.invisible()
        rv_player.invisible()
        noDataplayers.visible()
        swipePlayers.isRefreshing = false

        Log.d("showEmptyData", "masuk")
    }


    override fun showAllPlayers(data: List<PlayersItem>?) {
        Log.d("showAllPlayers", "masuk")

        swipePlayers.isRefreshing = false
        dataPlayers.clear()
        data?.let { dataPlayers.addAll(it) }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_player.layoutManager = layoutManager

        val adapter = PlayersAdapter(dataPlayers, context)
        rv_player.adapter = adapter

        adapter.notifyDataSetChanged()
        rv_player.scrollToPosition(0)
    }

}
