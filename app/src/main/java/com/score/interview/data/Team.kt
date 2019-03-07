package com.score.interview.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Team (

    @SerializedName("wins")
    @Expose
    var wins: Int = 0,

    @SerializedName("losses")
    @Expose
    var losses: Int = 0,

    @SerializedName("full_name")
    @Expose
    var fullName: String = "",

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @Ignore
    @SerializedName("players")
    @Expose
    var players: List<Player> = ArrayList()
)