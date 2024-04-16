package com.dicoding.asclepius.Data.Endpoint

import retrofit2.http.Query
import com.dicoding.asclepius.Data.Model.InformationListItem
import retrofit2.http.GET

interface ApiService {
    @JvmSuppressWildcards
    @GET("/v2/top-headlines")
    suspend fun getInformation(
        @Query("q") query: String,
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String
    ): InformationListItem
}