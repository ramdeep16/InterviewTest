package com.score.interview.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.score.interview.R
import com.score.interview.model.Team
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    lateinit var teamListViewModel: TeamListViewModel
    lateinit var teamListAdapter: TeamListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        init();

    }

    /*
     * Initialize all the variables
     * Set Layout Managers
     * */
    private fun init() {

        teamListViewModel = ViewModelProviders.of(this).get(TeamListViewModel::class.java)
        teams_list_recycler_view.layoutManager = LinearLayoutManager(this)
        setObservers()
    }

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
                    teamListAdapter = TeamListAdapter(teamList)
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.view_alphabetically -> {
                toast("view_alphabetically")
                true
            }
            R.id.view_by_wins -> {
                toast("view_by_wins")
                true
            }
            R.id.view_by_losses -> {
                toast("view_by_losses")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
