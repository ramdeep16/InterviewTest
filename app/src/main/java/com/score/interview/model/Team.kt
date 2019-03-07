package com.score.interview.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


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
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("players")
    @Expose
    var players: List<Player>?
)