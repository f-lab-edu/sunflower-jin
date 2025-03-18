package com.jin.sunflower.core.data.wikipedia

import com.jin.sunflower.core.data.RetrofitClient
import retrofit2.http.GET
import retrofit2.http.Path

interface WikipediaService {

    @GET("page/summary/{title}")
    suspend fun fetchPlantDescription(
        @Path("title") searchKeyword: String,
    ): WikipediaResponse

    companion object {
        val wikipediaService: WikipediaService =
            RetrofitClient.createService("https://en.wikipedia.org/api/rest_v1/")
    }
}
