package com.example.mediacenterfkam.footballapps.fragmentmain.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.Toast
import com.example.mediacenterfkam.footballapps.adapter.TeamsAdapter
import com.example.mediacenterfkam.footballapps.apidb.ApiRepository

import com.example.mediacenterfkam.footballapps.R
import com.example.mediacenterfkam.footballapps.response.TeamsItem
import com.example.mediacenterfkam.footballapps.utils.datatransfer.RxBus
import com.example.mediacenterfkam.footballapps.utils.datatransfer.RxEvent
import com.example.mediacenterfkam.footballapps.utils.invisible
import com.example.mediacenterfkam.footballapps.utils.visible
import com.google.gson.Gson
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_main_teams.*
import org.jetbrains.anko.support.v4.onRefresh

class MainTeams : Fragment(), TeamsView {

    private lateinit var presenter: TeamsPresenter
    private lateinit var teamDisposable: Disposable

    private var teams : MutableList<TeamsItem> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id =""

        setHasOptionsMenu(true)

        presenter = TeamsPresenter(this, ApiRepository(), Gson())

        teamDisposable = RxBus.listen(RxEvent.EventAddLeague::class.java).subscribe {
            id = it.leagueName
            presenter.getTeams(id)
        }

        swipeTeam.onRefresh {
            presenter.getTeams(id)
            progressTeams.invisible()
        }
    }

    override fun showLoading() {
        progressTeams.visible()
        rv_Teams.invisible()
        noDataTeams.invisible()
    }

    override fun hideLoading() {
        progressTeams.invisible()
        rv_Teams.visible()
        noDataTeams.invisible()
    }

    override fun showEmptyData() {
        progressTeams.invisible()
        swipeTeam.isRefreshing = false
        rv_Teams.invisible()
        noDataTeams.visible()
    }

    override fun showTeams(data: List<TeamsItem>?) {
        swipeTeam.isRefreshing = false
        teams.clear()
        teams.addAll(data!!)

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_Teams.layoutManager = layoutManager

        val adapter = TeamsAdapter(teams, context)
        rv_Teams.adapter = adapter

        adapter.notifyDataSetChanged()
        rv_Teams.scrollToPosition(0)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!teamDisposable.isDisposed) teamDisposable.dispose()
        Toast.makeText(context,"onDestroy", Toast.LENGTH_SHORT).show()
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.action_search, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Team"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                presenter.getTeamSearch(newText)
                return false
            }
        })

        searchView?.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {


                return true
            }
        })
    }


}
