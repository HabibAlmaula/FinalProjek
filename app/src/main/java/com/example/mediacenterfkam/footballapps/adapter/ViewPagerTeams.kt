package com.example.mediacenterfkam.footballapps.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.mediacenterfkam.footballapps.detail.detailteams.Overview
import com.example.mediacenterfkam.footballapps.detail.detailteams.Players
import com.example.mediacenterfkam.footballapps.fragmentmatch.CurrentMatch
import com.example.mediacenterfkam.footballapps.fragmentmatch.NextMatch

class ViewPagerTeams(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                Overview()
            }
            else -> Players()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 ->"Overview"
            else -> "Players"

        }

    }
}