package com.score.interview.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface TeamDao {

    // Team Entity
    @Query("SELECT * FROM Team")
    fun getAllTeamsFromDatabase(): Single<List<Team>>

    @Query("SELECT * from Team WHERE id = :teamID")
    fun getTeamWithTeamID(teamID: Int): Single<Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: Team)

    // Player Entity
    @Query("SELECT * from Player")
    fun getAllPlayers(teamID: Int): Single<List<Player>>

    @Query("SELECT * from Player WHERE id = :teamID")
    fun getPlayersWithTeamID(teamID: Int): Single<List<Player>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)
}