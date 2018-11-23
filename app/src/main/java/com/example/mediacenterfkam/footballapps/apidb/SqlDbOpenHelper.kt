package com.example.mediacenterfkam.footballapps.apidb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.mediacenterfkam.footballapps.response.MatchItem
import com.example.mediacenterfkam.footballapps.response.TeamsItem
import org.jetbrains.anko.db.*

class SqlDbOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "Favorites.db", null, 1) {

    companion object {
        private var instance: SqlDbOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): SqlDbOpenHelper {
            if (instance == null) {
                instance =
                        SqlDbOpenHelper(context.applicationContext)
            }

            return instance as SqlDbOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(MatchItem.TABLE_FAVORITES, true,
            MatchItem.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchItem.ID_EVENT to TEXT,
            MatchItem.DATE to TEXT,

            // home
            MatchItem.HOME_ID to TEXT,
            MatchItem.HOME_TEAM to TEXT,
            MatchItem.HOME_SCORE to TEXT,
            MatchItem.HOME_FORMATION to TEXT,
            MatchItem.HOME_GOAL_DETAILS to TEXT,
            MatchItem.HOME_SHOTS to TEXT,
            MatchItem.HOME_LINEUP_GOALKEEPER to TEXT,
            MatchItem.HOME_LINEUP_DEFENSE to TEXT,
            MatchItem.HOME_LINEUP_MIDFIELD to TEXT,
            MatchItem.HOME_LINEUP_FORWARD to TEXT,
            MatchItem.HOME_LINEUP_SUBSTITUTES to TEXT,

            // away
            MatchItem.AWAY_ID to TEXT,
            MatchItem.AWAY_TEAM to TEXT,
            MatchItem.AWAY_SCORE to TEXT,
            MatchItem.AWAY_FORMATION to TEXT,
            MatchItem.AWAY_GOAL_DETAILS to TEXT,
            MatchItem.AWAY_SHOTS to TEXT,
            MatchItem.AWAY_LINEUP_GOALKEEPER to TEXT,
            MatchItem.AWAY_LINEUP_DEFENSE to TEXT,
            MatchItem.AWAY_LINEUP_MIDFIELD to TEXT,
            MatchItem.AWAY_LINEUP_FORWARD to TEXT,
            MatchItem.AWAY_LINEUP_SUBSTITUTES to TEXT)

        db.createTable(TeamsItem.TEAM_TABLE, true,
            TeamsItem.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamsItem.TEAM_ID to TEXT,
            TeamsItem.TEAM_BADGE to TEXT,
            TeamsItem.TEAM_FANART to TEXT,
            TeamsItem.TEAM_NAME to TEXT,
            TeamsItem.TEAM_STADIUM to TEXT,
            TeamsItem.MANAGER to TEXT,
            TeamsItem.FORMED_YEAR to TEXT,
            TeamsItem.TEAM_DESCRIPTION to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchItem.TABLE_FAVORITES, true)
        db.dropTable(TeamsItem.TEAM_TABLE, true)
    }
}

val Context.database: SqlDbOpenHelper
    get() = SqlDbOpenHelper.getInstance(
        applicationContext
    )
