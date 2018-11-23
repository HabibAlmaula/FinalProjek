package com.example.mediacenterfkam.footballapps.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mediacenterfkam.footballapps.detail.detailmatch.DetailMatch
import com.example.mediacenterfkam.footballapps.fragmentmain.favorite.MainFavorite
import com.example.mediacenterfkam.footballapps.R
import com.example.mediacenterfkam.footballapps.response.MatchItem
import com.example.mediacenterfkam.footballapps.utils.ConvertDate
import kotlinx.android.synthetic.main.match_model.view.*
import org.jetbrains.anko.startActivity

class MatchAdapter(private val matchList: List<MatchItem>, val context: Context?) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.match_model, parent, false))

    }

    override fun getItemCount(): Int = matchList.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matchList[position])
    }


    class MatchViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)  {
        fun bind(match: MatchItem){
            val fragment3 = MainFavorite()

            itemView.txt_date.text =  match.dateEvent?.let { ConvertDate.newDate(it) }
            itemView.teamHome.text = match.strHomeTeam

            itemView.scoreHome.text = match.intHomeScore
            itemView.scoreAway.text = match.intAwayScore
            itemView.teamAway.text = match.strAwayTeam

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailMatch>("MATCH" to match )

            }
        }

    }
}