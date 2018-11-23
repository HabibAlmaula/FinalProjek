package com.example.mediacenterfkam.footballapps.fragmentmatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.mediacenterfkam.footballapps.R
import com.example.mediacenterfkam.footballapps.adapter.MatchAdapter
import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.response.MatchItem
import com.example.mediacenterfkam.footballapps.utils.invisible
import com.example.mediacenterfkam.footballapps.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_searchactivity.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast


class Searchactivity : AppCompatActivity(), MatchView  {

    lateinit var presenter: MatchPresenter
    private var matches : MutableList<MatchItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchactivity)
        presenter = MatchPresenter(this, ApiRepository(), Gson())
        val input = intent.getStringExtra("input")
        toast(input)
        presenter.getSearchMatch(input)


        swipeSearch.onRefresh {
            presenter.getSearchMatch(input)
        }


    }
    override fun showLoading() {
        progressSearch.visible()
        rvSearchMatch.invisible()
        nodataSearch.invisible()
        Log.d("ShowLoading", "masuk")
    }

    override fun hideLoading() {
        Log.d("hideLoading", "masuk")
        progressSearch.invisible()
        rvSearchMatch.visible()
        nodataSearch.invisible()
    }

    override fun showEmptyData() {
        Log.d("Emptydata", "masuk")
        progressSearch.invisible()
        rvSearchMatch.invisible()
        nodataSearch.visible()

    }

    override fun showMatchList(data: List<MatchItem>?) {
        swipeSearch.isRefreshing = false
        Log.d("showmatch", "masuk")
        matches.clear()
        data?.let { matches.addAll(it) }

        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        rvSearchMatch.layoutManager = layoutManager

        val adapter = MatchAdapter(matches, applicationContext)
        rvSearchMatch.adapter = adapter

        adapter.notifyDataSetChanged()
        rvSearchMatch.scrollToPosition(0)


    }


}
