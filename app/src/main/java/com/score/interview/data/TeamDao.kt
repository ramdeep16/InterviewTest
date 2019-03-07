package com.score.interview.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface TeamDao {

    // Team Entity
    @Query("SELECT * FROM Team")
    fun getAllTeamsFromDatabase(): List<Team>

    @Query("SELECT * from Team WHERE id = :teamID")
    fun getTeamWithTeamID(teamID: Int): Team

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: Team)

    // Player Entity
    @Query("SELECT * from Player WHERE teamID = :teamID")
    fun getPlayersWithTeamID(teamID: Int): List<Player>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: Player)
}