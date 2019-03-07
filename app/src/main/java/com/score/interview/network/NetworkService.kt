package com.score.interview.network

import com.score.interview.model.Team
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface NetworkService {

    @Headers("Content-Type: application/json")
    @GET("/scoremedia/nba-team-viewer/master/input.json")
    fun getTeamsList(): Single<List<Team>>
}