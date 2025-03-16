package com.jin.sunflower.core.domain.unsplash

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {
    @GET("photos/random")
    suspend fun fetchImageUrl(
        @Query("client_id") clientId: String,
        @Query("query") query: String,
        @Query("orientation") orientation: String,
    ): UnsplashResponse

    companion object {
        val unsplashApi: UnsplashService = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashService::class.java)
    }
}
