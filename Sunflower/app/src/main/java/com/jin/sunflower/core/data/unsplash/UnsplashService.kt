package com.jin.sunflower.core.data.unsplash

import com.jin.sunflower.core.data.RetrofitClient
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {
    @GET("photos/random")
    suspend fun fetchPlantImageUrl(
        @Query("client_id") clientId: String,
        @Query("query") query: String,
        @Query("orientation") orientation: String,
    ): UnsplashResponse

    companion object {
        val unsplashApi: UnsplashService = RetrofitClient.createService("https://api.unsplash.com/")
    }
}
