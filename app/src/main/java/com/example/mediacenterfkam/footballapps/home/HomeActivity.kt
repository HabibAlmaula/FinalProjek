package com.example.mediacenterfkam.footballapps.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.mediacenterfkam.footballapps.apidb.ApiRepository
import com.example.mediacenterfkam.footballapps.fragmentmain.favorite.MainFavorite
import com.example.mediacenterfkam.footballapps.fragmentmain.MainMatch
import com.example.mediacenterfkam.footballapps.fragmentmain.teams.MainTeams
import com.example.mediacenterfkam.footballapps.R
import com.example.mediacenterfkam.footballapps.response.LeagueResponse
import com.example.mediacenterfkam.footballapps.response.LeaguesItem
import com.example.mediacenterfkam.footballapps.utils.datatransfer.RxBus
import com.example.mediacenterfkam.footballapps.utils.datatransfer.RxEvent
import com.example.mediacenterfkam.footballapps.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.actionMenuView
import org.jetbrains.anko.colorAttr
import org.jetbrains.anko.searchView

class HomeActivity : AppCompatActivity(), HomeView {

    private lateinit var presenter: HomePresenter
    lateinit var leaguesItem: LeaguesItem

    private val fragment1 = MainMatch()
    private val fragment2 = MainTeams()
    private val fragment3 = MainFavorite()
    private var active: Fragment = fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbarMain)
        homeSpinner.setBackgroundColor(resources.getColor(R.color.white))


        val apiRepository = ApiRepository()
        val gson = Gson()

        presenter = HomePresenter(this, apiRepository, gson)
        presenter.getAllLeague()


        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_content, fragment1, MainMatch::class.java.simpleName)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_content, fragment2, MainTeams::class.java.simpleName)
            .hide(fragment2)
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_content, fragment3, MainFavorite::class.java.simpleName)
            .hide(fragment3)
            .commit()





        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_macthes -> {
                homeSpinner.visible()
                supportFragmentManager.beginTransaction().hide(active).detach(fragment3).show(fragment1).commit()
                active = fragment1



                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                homeSpinner.visible()
                supportFragmentManager.beginTransaction().hide(active).detach(fragment3).show(fragment2).commit()
                active = fragment2

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                homeSpinner.visibility = View.GONE

                supportFragmentManager.beginTransaction().hide(active).attach(fragment3).show(fragment3).commit()
                active = fragment3
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun showLeagueList(data: LeagueResponse) {
        homeSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data.leagues)
        homeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leaguesItem = homeSpinner.selectedItem as LeaguesItem

                val idLeague = leaguesItem.idLeague!!.toString()
                RxBus.publish(RxEvent.EventAddLeague(idLeague))

            }
        }
    }


    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_search, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Team"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // mPresenter.searchTeam(newText)
                return false
            }
        })

        searchView?.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                //   mPresenter.getTeamData("4328")
                return true
            }
        })


        return super.onCreateOptionsMenu(menu)
    }*/



}
