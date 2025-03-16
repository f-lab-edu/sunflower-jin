package com.jin.sunflower.core.data.wikipedia

import com.jin.sunflower.core.domain.wikipedia.WikipediaService

class WikipediaDataSource(private val apiService: WikipediaService) {
    suspend fun getPlantDescription(query: String): String {
        return try {
            val response = apiService.fetchPlantDescription(query)
            response.extract
        } catch (e: Exception) {
            println("Wikipedia Error : ${e.message}")
            ""
        }
    }
}
