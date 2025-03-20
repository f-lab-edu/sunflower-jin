package com.jin.sunflower.core.data.local

import com.jin.sunflower.core.data.unsplash.UnsplashDataSource
import com.jin.sunflower.core.data.wikipedia.WikipediaDataSource
import com.jin.sunflower.core.model.Plant
import java.time.Instant

class InMemoryLocalPlantDataSource(
    private val unsplashApi: UnsplashDataSource,
    private val wikipediaApi: WikipediaDataSource,
) {

    private val plantNameList = listOf("Apple", "Avocado", "Beet", "Eggplant")

    suspend fun loadPlantList(): List<Plant> {
        return plantNameList.mapNotNull { searchPlantByName(it) }
    }

    private suspend fun searchPlantByName(plantName: String): Plant? {
        return try {
            val unsplashImage = unsplashApi.searchPlantImageUrl(plantName)
            val wikipediaDescription = wikipediaApi.searchPlantDescription(plantName)
            Plant(
                name = plantName,
                description = wikipediaDescription.description,
                growZoneNumber = 3,
                wateringIntervalInDays = 30,
                imageUrl = unsplashImage.imageUrl,
                addedAt = Instant.now(),
                lastWateredAt = Instant.now()
            )
        } catch (e: Exception) {
            println("Error : ${e.message}")
            null
        }
    }
}
