package com.jin.sunflower.core.data.wikipedia

import com.jin.sunflower.core.domain.entity.WikipediaDescription

class WikipediaDataSource(private val apiService: WikipediaService) {
    suspend fun searchPlantDescription(keyword: String): WikipediaDescription {
        return try {
            val response: WikipediaResponse = apiService.fetchPlantDescription(keyword)
            WikipediaDescription(description = response.extract)
        } catch (e: Exception) {
            println("Wikipedia Error : ${e.message}")
            WikipediaDescription(description = "")
        }
    }
}
