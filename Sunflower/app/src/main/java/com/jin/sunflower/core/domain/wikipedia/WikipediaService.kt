package com.jin.sunflower.core.domain.wikipedia

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WikipediaService {

    @GET("page/summary/{title}")
    suspend fun fetchPlantDescription(
        @Path("title") searchKeyword: String,
    ): WikipediaResponse

    companion object {
        val wikipediaService: WikipediaService = Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/api/rest_v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WikipediaService::class.java)
    }
}
