package com.score.interview.activity

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.score.interview.R
import com.score.interview.data.Player
import kotlinx.android.synthetic.main.custom_player_layout.view.*

class PlayersAdapter (private val context: Context, private val playerList: List<Player>): RecyclerView.Adapter<PlayersViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayersViewHolder {
        return PlayersViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.custom_player_layout,p0,false))
    }

    override fun getItemCount(): Int {

        return playerList.size
    }

    override fun onBindViewHolder(playerViewHolder: PlayersViewHolder, position: Int) {

        playerViewHolder.itemView.full_name_text_view.text = String.format(playerList[position].firstName +" "+ playerList[position].lastName)
        playerViewHolder.itemView.player_position_text_view.text = String.format(context.getString(R.string.position), playerList[position].position)
        playerViewHolder.itemView.player_number_text_view.text = String.format(context.getString(R.string.number), playerList[position].number)
    }
}

class PlayersViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

}