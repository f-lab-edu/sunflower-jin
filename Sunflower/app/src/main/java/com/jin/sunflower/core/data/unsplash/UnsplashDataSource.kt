package com.jin.sunflower.core.data.unsplash

import com.jin.sunflower.BuildConfig
import com.jin.sunflower.core.domain.unsplash.UnsplashService

class UnsplashDataSource(private val apiService: UnsplashService) {
    suspend fun searchPlantImageUrl(keyword: String): String {
        return try {
            val response = apiService.fetchPlantImageUrl(
                clientId = BuildConfig.UNSPLASH_API_KEY,
                query = keyword,
                orientation = "landscape"
            )
            response.urls.regular
        } catch (e: Exception) {
            println("Unsplash Error : ${e.message}")
            "https://picsum.photos/300/200?random=1"
        }
    }
}
