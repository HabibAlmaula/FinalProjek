package com.example.mediacenterfkam.footballapps.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayersItem(
    val idPlayer: String?,
    val idSoccerXML: String?,
    val idPlayerManager: String?,
    val strNationality: String?,
    val strPlayer: String?,
    val strTeam: String?,
    val intSoccerXMLTeamID: String?,
    val dateBorn: String?,
    val dateSigned: String?,
    val strSigning: String?,
    val strBirthLocation: String?,
    val strDescriptionEN: String?,
    val strGender: String?,
    val strPosition: String?,
    val strHeight: String?,
    val strWeight: String?,
    val idTeam: String?,
    val strCutout: String?,
    val strFanart1: String?
) : Parcelable