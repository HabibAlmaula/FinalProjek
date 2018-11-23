package com.example.mediacenterfkam.footballapps.detail.detailteams

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mediacenterfkam.footballapps.R
import com.example.mediacenterfkam.footballapps.adapter.ViewPagerAdapter
import com.example.mediacenterfkam.footballapps.adapter.ViewPagerTeams
import com.example.mediacenterfkam.footballapps.response.TeamsItem
import com.example.mediacenterfkam.footballapps.utils.datatransfer.RxBus
import com.example.mediacenterfkam.footballapps.utils.datatransfer.RxEvent
import kotlinx.android.synthetic.main.activity_detail_teams.*

class DetailTeams : AppCompatActivity() {

    private lateinit var teamsData : TeamsItem
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_teams)

        setSupportActionBar(main_toolbar)

        teamsData = if (savedInstanceState !=null){
            savedInstanceState.getParcelable("TEAMS")
        }else{
            intent.getParcelableExtra("TEAMS")
        }
        val bundle = Bundle()
        bundle.putParcelable("TEAMSS", teamsData)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = teamsData.strTeam


        adapter = ViewPagerAdapter(supportFragmentManager)
        val overview = Overview()
        val players = Players()
        overview.arguments = bundle
        players.arguments = bundle
        adapter.populateFragment(overview, "Overview")
        adapter.populateFragment(players, "Players")

        vp_Teams.adapter = adapter
        tabsTeam.setupWithViewPager(vp_Teams)
        main_collapsing.setExpandedTitleColor(Color.TRANSPARENT)
        setupData(teamsData)
    }

    private fun setupData(teamsData: TeamsItem?) {
        Glide.with(applicationContext)
            .load(teamsData?.strTeamFanart1)
            .apply(RequestOptions().placeholder(R.drawable.ic_no_data))
            .into(main_backdrop)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("TEAMS", teamsData)
    }
}
