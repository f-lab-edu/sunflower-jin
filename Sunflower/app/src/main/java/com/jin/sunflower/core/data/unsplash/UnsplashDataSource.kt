package com.jin.sunflower.core.data.unsplash

import com.jin.sunflower.BuildConfig
import com.jin.sunflower.core.domain.unsplash.UnsplashService

class UnsplashDataSource(private val apiService: UnsplashService) {

    suspend fun getImageUrl(query: String): String {
        return try {
            val response = apiService.fetchImageUrl(
                clientId = BuildConfig.UNSPLASH_API_KEY,
                query = query,
                orientation = "landscape"
            )
            response.urls.regular
        } catch (e: Exception) {
            println("Unsplash Error : ${e.message}")
            "https://picsum.photos/300/200?random=1"
        }
    }
}
