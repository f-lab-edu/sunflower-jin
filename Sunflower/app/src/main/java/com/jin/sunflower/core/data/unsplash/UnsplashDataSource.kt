package com.jin.sunflower.core.data.unsplash

import com.jin.sunflower.BuildConfig
import com.jin.sunflower.core.domain.entity.UnsplashImage

class UnsplashDataSource(private val apiService: UnsplashService) {
    suspend fun searchPlantImageUrl(keyword: String): UnsplashImage {
        return try {
            val response = apiService.fetchPlantImageUrl(
                clientId = BuildConfig.UNSPLASH_API_KEY,
                query = keyword,
                orientation = "landscape"
            )
            UnsplashImage(imageUrl = response.urls.regular)
        } catch (e: Exception) {
            println("Unsplash Error : ${e.message}")
            UnsplashImage(imageUrl = "https://picsum.photos/300/200?random=1")
        }
    }
}
