package com.example.mediacenterfkam.footballapps.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamsItem(
    val id : Long?,
    val idTeam: String?,
    val strTeamBadge: String?,
    val strTeamFanart1: String?,
    val strTeam: String?,
    val strStadium: String?,
    val strManager: String?,
    val intFormedYear : String?,
    val strDescriptionEN: String?) : Parcelable {
    companion object {
    const val TEAM_TABLE = "TEAM_TABLE"
        const val ID = "ID"
        const val TEAM_ID = "TEAM_ID"
        const val TEAM_BADGE = "TEAM_BADGE"
        const val TEAM_FANART = "TEAM_FANART"
        const val TEAM_NAME = "TEAM_NAME"
        const val TEAM_STADIUM = "TEAM_STADIUM"
        const val MANAGER = "MANAGER"
        const val FORMED_YEAR = "FORMED_YEAR"
        const val TEAM_DESCRIPTION = "TEAM_DESCRIPTION"
    }
}