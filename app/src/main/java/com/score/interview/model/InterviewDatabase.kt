package com.score.interview.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Team::class, Player::class], version = 1)
abstract class InterviewDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao
}