package com.score.interview.network

import com.score.interview.data.Team
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.google.gson.GsonBuilder
import com.score.interview.helperClasses.MyApplication
import android.arch.persistence.room.Room
import com.score.interview.data.InterviewDatabase
import io.reactivex.Flowable
import io.reactivex.Observable


class TeamsAPIClass {

    private var service: NetworkService
    private val mRoomDatabaseHelper = Room.databaseBuilder(MyApplication.getInstance(), InterviewDatabase::class.java, "scoreinterview")
        .allowMainThreadQueries()
        .build()

    // Primary Constructor
    init {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        service = retrofit.create(NetworkService::class.java)
    }

    fun getTeams() = Flowable.concat(getTeamsFromDatabase().toFlowable(), getTeamsListFromNetwork().toFlowable())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.io())

    // Get team list from Input.json file
    private fun getTeamsListFromNetwork(): Single<List<Team>> {

        return service.getTeamsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .unsubscribeOn(Schedulers.io())
            .doAfterSuccess(::insertTeamsIntoDatabase)
    }

    private fun insertTeamsIntoDatabase(teamsList: List<Team>) {

        for (team in teamsList) {
            if (team.id == 0) {
                continue
            }

            if (team.players.isNotEmpty()) {
                for (player in team.players) {
                    player.teamID = team.id
                    mRoomDatabaseHelper.teamDao().insertPlayer(player)
                }
            }
            mRoomDatabaseHelper.teamDao().insertTeam(team)
        }
    }

    private fun getTeamsFromDatabase(): Single<List<Team>> {

        val teamList = mRoomDatabaseHelper.teamDao().getAllTeamsFromDatabase()
        for (team in teamList) {
            if (team.id == 0) {
                continue
            }
            team.players = mRoomDatabaseHelper.teamDao().getPlayersWithTeamID(team.id)
        }

        return Observable.fromIterable(teamList).toList()
    }

    // Methods used for TeamDetailsActivity
    fun getTeamsWithID(teamID: Int) = getTeamFromDatabaseWithTeamID(teamID).toFlowable()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .unsubscribeOn(Schedulers.io())

    private fun getTeamFromDatabaseWithTeamID(teamID: Int): Single<Team> {
        val team = mRoomDatabaseHelper.teamDao().getTeamWithTeamID(teamID)

        val players = mRoomDatabaseHelper.teamDao().getPlayersWithTeamID(teamID)

        team.players = players

        return Single.just(team)

    }
}