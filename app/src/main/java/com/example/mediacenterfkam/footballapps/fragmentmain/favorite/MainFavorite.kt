package com.example.mediacenterfkam.footballapps.fragmentmain.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mediacenterfkam.footballapps.adapter.MatchAdapter
import com.example.mediacenterfkam.footballapps.R
import com.example.mediacenterfkam.footballapps.response.MatchItem
import com.example.mediacenterfkam.footballapps.utils.invisible
import com.example.mediacenterfkam.footballapps.utils.visible
import kotlinx.android.synthetic.main.fragment_main_favorite.*
import org.jetbrains.anko.support.v4.onRefresh

class MainFavorite : Fragment(), FavoriteView {
    private lateinit var presenter: FavoritePresenter
    private var events: MutableList<MatchItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = FavoritePresenter(this)

        presenter.getFavoritesAll(requireContext())

        swipeFavorite.onRefresh {
            presenter.getFavoritesAll(requireContext())
            progressFav.invisible()
        }


    }

    override fun showLoading() {
        noDatafavorite.invisible()
        progressFav.visible()
        rvfavorite.invisible()
    }

    override fun hideLoading() {
        noDatafavorite.invisible()
        progressFav.invisible()
        rvfavorite.visible()
    }

    override fun showEmptyData() {
        noDatafavorite.visible()
        progressFav.invisible()
        rvfavorite.invisible()
        swipeFavorite.isRefreshing = false
    }

    override fun showMatchListFavorite(data: List<MatchItem>) {
        swipeFavorite.isRefreshing = false
        events.clear()
        events.addAll(data)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = MatchAdapter(events, context)
        rvfavorite.layoutManager = layoutManager
        rvfavorite.adapter = adapter

        adapter.notifyDataSetChanged()
        rvfavorite.scrollToPosition(0)
        swipeFavorite.isRefreshing = false
    }
}
