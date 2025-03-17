package com.jin.sunflower.core.domain.wikipedia

import com.jin.sunflower.core.domain.RetrofitClient
import retrofit2.http.GET
import retrofit2.http.Path

interface WikipediaService {

    @GET("page/summary/{title}")
    suspend fun fetchPlantDescription(
        @Path("title") searchKeyword: String,
    ): WikipediaResponse

    companion object {
        val wikipediaService: WikipediaService = RetrofitClient.createService(
            "https://en.wikipedia.org/api/rest_v1/", WikipediaService::class.java
        )
    }
}
