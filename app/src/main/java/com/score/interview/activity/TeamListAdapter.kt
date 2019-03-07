package com.score.interview.activity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.score.interview.R
import com.score.interview.model.Team
import kotlinx.android.synthetic.main.custom_team_list_layout.view.*

class TeamListAdapter(private val teamList: List<Team>): RecyclerView.Adapter<TeamListAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.custom_team_list_layout, p0, false))
    }

    override fun getItemCount(): Int {

        return teamList.size
    }

    override fun onBindViewHolder(teamHolder: TeamViewHolder, position: Int) {

        teamHolder.itemView.name.text = teamList[position].fullName
    }


    class TeamViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    }
}