package com.jin.sunflower.core.data.repository

interface PlantRepository {
    suspend fun fetchImageUrl(): String
}
