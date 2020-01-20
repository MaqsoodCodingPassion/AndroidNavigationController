package com.servicenow.service

import com.servicenow.model.GamesListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("/api/jsonBlob/{apiKey}")
    fun getGamesList(@Path("apiKey") apiKey: String?): Single<GamesListResponse>
}
