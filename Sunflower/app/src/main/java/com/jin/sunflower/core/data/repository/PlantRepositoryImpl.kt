package com.jin.sunflower.core.data.repository

import com.jin.sunflower.BuildConfig
import com.jin.sunflower.core.network.UnsplashApiService

class PlantRepositoryImpl(private val apiService: UnsplashApiService) : PlantRepository {

    override suspend fun fetchImageUrl(): String {
        return try {
            val response = apiService.getRandomImage(
                clientId = BuildConfig.UNSPLASH_API_KEY,
                query = "nature",
                orientation = "landscape"
            )
            response.urls.regular
        } catch (e: Exception) {
            println("Error : ${e.message}")
            "https://picsum.photos/300/200?random=1"
        }
    }
}
