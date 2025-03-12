package com.jin.sunflower.core.network

import com.jin.sunflower.core.model.UnsplashResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("photos/random")
    suspend fun getRandomImage(
        @Query("client_id") clientId: String,
        @Query("query") query: String,
        @Query("orientation") orientation: String,
    ): UnsplashResponse
}
