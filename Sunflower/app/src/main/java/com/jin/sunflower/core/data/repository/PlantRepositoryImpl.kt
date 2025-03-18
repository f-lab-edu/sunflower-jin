package com.jin.sunflower.core.data.repository

import com.jin.sunflower.core.data.unsplash.UnsplashDataSource
import com.jin.sunflower.core.data.wikipedia.WikipediaDataSource
import com.jin.sunflower.core.domain.repository.PlantRepository
import com.jin.sunflower.core.model.Plant
import java.time.Instant

class PlantRepositoryImpl(
    private val unsplashApi: UnsplashDataSource,
    private val wikipediaApi: WikipediaDataSource
) : PlantRepository {

    // 기존에 ViewModel 에 정의되어있던 mockData 의 위치를 repository 로 옮김.
    // -> viewModel 은 view 에 데이터가 전달되는 다리역활이기때문.
    // -> 데이터가 가공되는 곳은 여기임으로 mockData 도 여기에 선언.

    private val plantNameList = listOf("Apple", "Avocado", "Beet", "Eggplant")

    override suspend fun loadPlantList(): List<Plant?> {
        return plantNameList.map { plantName ->
            searchPlantByName(plantName)
        }
    }

    override suspend fun searchPlantByName(plantName: String): Plant? {
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
