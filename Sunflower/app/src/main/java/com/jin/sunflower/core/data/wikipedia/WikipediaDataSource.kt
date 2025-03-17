package com.jin.sunflower.core.data.wikipedia

import com.jin.sunflower.core.domain.wikipedia.WikipediaService

class WikipediaDataSource(private val apiService: WikipediaService) {
    suspend fun searchPlantDescription(keyword: String): String {
        return try {
            val response = apiService.fetchPlantDescription(keyword)
            response.extract
        } catch (e: Exception) {
            println("Wikipedia Error : ${e.message}")
            ""
        }
    }
}
