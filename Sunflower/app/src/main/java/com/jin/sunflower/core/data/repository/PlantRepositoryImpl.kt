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

    private val plantList = listOf(
        Plant(
            id = 1,
            name = "Apple",
            description = "",
            growZoneNumber = 3,
            wateringIntervalInDays = 30,
            imageUrl = "",
            addedAt = Instant.now(),
            lastWateredAt = Instant.now(),
        ),
        Plant(
            id = 2,
            name = "Avocado",
            description = "",
            growZoneNumber = 3,
            wateringIntervalInDays = 30,
            imageUrl = "",
            addedAt = Instant.now(),
            lastWateredAt = Instant.now(),
        ),
        Plant(
            id = 3,
            name = "Beet",
            description = "",
            growZoneNumber = 3,
            wateringIntervalInDays = 30,
            imageUrl = "",
            addedAt = Instant.now(),
            lastWateredAt = Instant.now(),
        ),
        Plant(
            id = 4,
            name = "Eggplant",
            description = "",
            growZoneNumber = 3,
            wateringIntervalInDays = 30,
            imageUrl = "",
            addedAt = Instant.now(),
            lastWateredAt = Instant.now(),
        )
    )

    override suspend fun loadPlantList(): List<Plant> {
        return plantList.map { plant ->
            searchPlantByName(plant)
        }
    }

    override suspend fun searchPlantByName(plant: Plant): Plant {
        return try {
            val unsplashImage = unsplashApi.searchPlantImageUrl(plant.name)
            val wikipediaDescription = wikipediaApi.searchPlantDescription(plant.name)
            plant.copy(
                description = wikipediaDescription.description,
                imageUrl = unsplashImage.imageUrl
            )
        } catch (e: Exception) {
            println("Error : ${e.message}")
            plant
        }
    }
}
