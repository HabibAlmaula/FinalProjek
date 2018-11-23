package com.example.mediacenterfkam.footballapps.detail.detailteams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.mediacenterfkam.footballapps.R
import com.example.mediacenterfkam.footballapps.response.TeamsItem
import com.example.mediacenterfkam.footballapps.utils.datatransfer.RxBus
import com.example.mediacenterfkam.footballapps.utils.datatransfer.RxEvent
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_overview.*
class Overview : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teamData : TeamsItem? = arguments?.getParcelable("TEAMSS")
        setupData(teamData)

    }

    private fun setupData(teamData: TeamsItem?) {
        Glide.with(this)
            .load(teamData?.strTeamBadge)
            .into(iv_teamBadges)

        tv_name.text = teamData?.strTeam
        tv_stadium.text = teamData?.strStadium
        tv_manager.text = teamData?.strManager
        tv_overView.text = teamData?.strDescriptionEN


    }


}
