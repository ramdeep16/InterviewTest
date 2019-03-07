package com.score.interview.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

@Entity
data class Player(

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("first_name")
    @Expose
    var firstName: String = "",

    @SerializedName("last_name")
    @Expose
    var lastName: String = "",

    @SerializedName("position")
    @Expose
    var position: String = "",

    @SerializedName("number")
    @Expose
    var number: Int = 0,

    var teamID: Int = 0
){

}