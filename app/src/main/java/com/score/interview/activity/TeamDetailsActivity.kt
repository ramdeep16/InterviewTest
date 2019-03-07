package com.score.interview.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.score.interview.R
import com.score.interview.data.Team
import com.score.interview.helperClasses.Constants

import kotlinx.android.synthetic.main.activity_team_details.*
import kotlinx.android.synthetic.main.content_team_details.*
import org.jetbrains.anko.toast
import android.view.MenuItem


class TeamDetailsActivity : AppCompatActivity() {

    lateinit var teamListViewModel: TeamListViewModel
    lateinit var playerAdapter: PlayersAdapter
    private var  teamID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        init()

    }

    private fun init() {
        teamListViewModel = ViewModelProviders.of(this).get(TeamListViewModel::class.java)
        players_recycler_view.layoutManager = LinearLayoutManager(this)

        val intent = intent
        if (intent != null) {
            val bundle = intent.getBundleExtra(Constants.DATA)
            if (bundle != null) {
                teamID = bundle.getInt(Constants.TEAMID)
            }
        }

        setObservers()
    }

    private fun setObservers() {

        teamListViewModel.getProgressBarLiveData().observe(this, Observer { value ->
            if (value != null && value) {
                player_progress_bar.visibility = View.VISIBLE
            } else {
                player_progress_bar.visibility = View.GONE
            }
        })

        teamListViewModel.getMessageLiveData().observe(this, Observer { value ->
            if (value != null) {
                toast(value)
            }
        })

        teamListViewModel.getTeamDetail(teamID).observe(this, Observer { team ->
            if (team != null) {
                fillFields(team)
                players_recycler_view.post {
                    playerAdapter = PlayersAdapter(this, team.players)
                    players_recycler_view.adapter = playerAdapter
                }
            }
        })
    }

    private fun fillFields(team: Team) {

        team_name_text_view.text = team.fullName
        player_size_text_view.text = String.format(getString(R.string.players),team.players.size)
        win_text_view.text = String.format(getString(R.string.win), team.wins)
        loss_text_view.text = String.format(getString(R.string.loss), team.losses)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}
