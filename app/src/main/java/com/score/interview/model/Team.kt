package com.score.interview.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Team(

    @SerializedName("wins")
    @Expose
    var wins: Int,
    @SerializedName("losses")
    @Expose
    var losses: Int,
    @SerializedName("full_name")
    @Expose
    var fullName: String,
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("players")
    @Expose
    var players: List<Player>?
)