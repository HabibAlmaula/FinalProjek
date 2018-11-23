package com.example.mediacenterfkam.footballapps.utils.datatransfer


class RxEvent {
    data class EventAddLeague(val leagueName: String)
    data class EventAddMatch(val matchName : String)
}