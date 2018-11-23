package com.example.mediacenterfkam.footballapps.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.mediacenterfkam.footballapps.fragmentmatch.CurrentMatch
import com.example.mediacenterfkam.footballapps.fragmentmatch.NextMatch


class ViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager){
    var fragment = arrayListOf<Fragment>()
    var title = arrayListOf<String>()

    override fun getItem(position: Int) = fragment[position]

    override fun getCount() = fragment.size

    override fun getPageTitle(position: Int) = title[position]

    fun populateFragment(mfragment: Fragment, mtitle : String){
        fragment.add(mfragment)
        title.add(mtitle)
    }
}