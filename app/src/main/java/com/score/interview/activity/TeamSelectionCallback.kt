package com.score.interview.activity

import com.score.interview.data.Team

interface TeamSelectionCallback {

    fun teamSelected(teamSelected: Team)
}