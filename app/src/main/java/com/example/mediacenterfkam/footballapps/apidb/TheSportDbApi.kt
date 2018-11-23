package com.example.mediacenterfkam.footballapps.apidb

import android.net.Uri
import com.example.mediacenterfkam.footballapps.BuildConfig

object TheSportsDbApi {

    fun getAllLeague(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("all_leagues.php")
            .build()
            .toString()
    }

    //hanya untuk ambil BadgeTeam
    fun getDetailTeams(id: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()

    }

    fun getCurrentLeague(id: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()

    }

    fun getNextLeague(id: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()

    }

    fun getTeams (id : String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookup_all_teams.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()
    }

    fun getPlayers(id: String): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookup_all_players.php")
            .appendQueryParameter("id", id)
            .build()
            .toString()

    }

    fun getSearchMatch(id : String): String{
       return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchevents.php")
            .appendQueryParameter("e", id)
            .build()
            .toString()
    }

    fun getSearchTeam(id : String):String{

        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchteams.php")
            .appendQueryParameter("t", id)
            .build()
            .toString()
    }


}