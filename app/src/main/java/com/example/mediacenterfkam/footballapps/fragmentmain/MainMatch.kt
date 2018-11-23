package com.example.mediacenterfkam.footballapps.fragmentmain


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import com.example.mediacenterfkam.footballapps.adapter.ViewPagerAdapter

import com.example.mediacenterfkam.footballapps.R
import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.fragmentmatch.Searchactivity
import com.example.mediacenterfkam.footballapps.fragmentmatch.CurrentMatch
import com.example.mediacenterfkam.footballapps.fragmentmatch.MatchPresenter
import com.example.mediacenterfkam.footballapps.fragmentmatch.NextMatch
import com.example.mediacenterfkam.footballapps.utils.datatransfer.RxBus
import com.example.mediacenterfkam.footballapps.utils.datatransfer.RxEvent
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main_match.*
import org.jetbrains.anko.startActivity


class MainMatch : Fragment() {

    private lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        adapter = ViewPagerAdapter(childFragmentManager)
        adapter.populateFragment(CurrentMatch(), "Current Match")
        adapter.populateFragment(NextMatch(), "Next Match")
        viewpagerMatch.adapter = adapter
        tabs.setupWithViewPager(viewpagerMatch)
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.action_search, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Match"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(input: String): Boolean {
                context?.startActivity<Searchactivity>("input" to input)

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
               // mPresenter.searchTeam(newText)

               // RxBus.publish(RxEvent.EventAddMatch(newText))


                return false
            }
        })

        searchView?.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {
             //   mPresenter.getTeamData("4328")
                return true
            }
        })
    }




}
