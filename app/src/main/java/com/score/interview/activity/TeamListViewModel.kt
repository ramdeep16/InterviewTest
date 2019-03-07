package com.score.interview.activity

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.score.interview.model.Team
import com.score.interview.network.TeamsAPIClass
import io.reactivex.disposables.CompositeDisposable

class TeamListViewModel : ViewModel() {

    private val mCompositeDisposable = CompositeDisposable()
    private val teamsAPIClass: TeamsAPIClass = TeamsAPIClass()

    private lateinit var progressBarLiveData: MutableLiveData<Boolean>
    private lateinit var messageLiveData: MutableLiveData<String>
    private lateinit var teamsListLiveData: MutableLiveData<List<Team>>

    fun getProgressBarLiveData(): MutableLiveData<Boolean> {

        progressBarLiveData = MutableLiveData()
        progressBarLiveData.value = false
        return progressBarLiveData
    }

    fun getMessageLiveData(): MutableLiveData<String> {

        messageLiveData = MutableLiveData()
        return messageLiveData
    }

    fun getTeamList(): MutableLiveData<List<Team>> {

        teamsListLiveData = MutableLiveData()
        fetchTeams()
        return teamsListLiveData
    }

    private fun fetchTeams() {
        mCompositeDisposable.add(
            teamsAPIClass.getTeamsList()
                .subscribe({ teams ->
                    if (teams != null) {
                        teamsListLiveData.value = teams
                    }
                }, {
                    handleError(throwable = it)
                })
        )
    }

    private fun handleError(throwable: Throwable) {
        messageLiveData.value = throwable.localizedMessage
    }
}