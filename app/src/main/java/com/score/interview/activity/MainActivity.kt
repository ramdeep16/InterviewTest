package com.score.interview.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.score.interview.R
import com.score.interview.data.Team
import com.score.interview.helperClasses.Constants
import com.score.interview.helperClasses.SortOptions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), TeamSelectionCallback {

    private lateinit var teamListViewModel: TeamListViewModel
    private lateinit var teamListAdapter: TeamListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init()
    }

    /**
     * Initialize all the variables
     * Set Layout Managers
     * */
    private fun init() {

        teamListViewModel = ViewModelProviders.of(this).get(TeamListViewModel::class.java)
        teams_list_recycler_view.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        progress_bar.visibility = View.VISIBLE
        setObservers()
    }

    /**
     * Observing Data from View Model using Live Data
     */
    private fun setObservers() {

        teamListViewModel.getProgressBarLiveData().observe(this, Observer { value ->
            if (value != null && value) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })

        teamListViewModel.getMessageLiveData().observe(this, Observer { value ->
            if (value != null) {
                toast(value)
            }
        })

        teamListViewModel.getTeamList().observe(this, Observer { teamList ->
            if (teamList != null) {
                teams_list_recycler_view.post {
                    teamListAdapter = TeamListAdapter(this, teamList, this)
                    teams_list_recycler_view.adapter = teamListAdapter
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.view_alphabetically -> {
                teamListViewModel.sortingOptionSelected = SortOptions.ALPHABETICAL
                true
            }
            R.id.view_by_wins_ascending -> {
                teamListViewModel.sortingOptionSelected = SortOptions.WINS_ASCENDING
                true
            }
            R.id.view_by_losses_ascending -> {
                teamListViewModel.sortingOptionSelected = SortOptions.LOSSES_ASCENDING
                true
            }
            R.id.view_by_wins_descending -> {
                teamListViewModel.sortingOptionSelected = SortOptions.WINS_DESCENDING
                true
            }
            R.id.view_by_losses_descending -> {
                teamListViewModel.sortingOptionSelected = SortOptions.LOSSES_ASCENDING
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * TeamSelectionCallback implementation method
     * method runs when team is selected from the TeamListAdapter
     */
    override fun teamSelected(teamSelected: Team) {

        // Only sending the team ID to the Next Activity
        // Will fetch the data using this teamID in the nextActivity
        val intent = Intent(this, TeamDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(Constants.TEAMID, teamSelected.id)
        intent.putExtra(Constants.DATA, bundle)
        startActivity(intent)
    }
}
