package com.example.mediacenterfkam.footballapps.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mediacenterfkam.footballapps.R
import com.example.mediacenterfkam.footballapps.detail.DetailPlayers
import com.example.mediacenterfkam.footballapps.response.PlayersItem
import kotlinx.android.synthetic.main.players_model.view.*
import org.jetbrains.anko.startActivity

class PlayersAdapter(private val playerList : List<PlayersItem>,
                     private val context: Context?):
        RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        return PlayersViewHolder(LayoutInflater.from(context).inflate(R.layout.players_model, parent, false))
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        holder.bind(playerList[position])
    }


    class PlayersViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        fun bind (player : PlayersItem){
            itemView.tv_name.text = player.strPlayer
            itemView.tv_position.text = player.strPosition

            Glide.with(itemView.context)
                .load(player.strCutout)
                .into(itemView.iv_player)

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailPlayers>("PLAYERS" to player)
            }
        }

    }
}