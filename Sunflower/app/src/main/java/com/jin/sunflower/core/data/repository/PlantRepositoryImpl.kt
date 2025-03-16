package com.jin.sunflower.core.data.repository

import com.jin.sunflower.core.data.unsplash.UnsplashDataSource
import com.jin.sunflower.core.data.wikipedia.WikipediaDataSource
import com.jin.sunflower.core.domain.repository.PlantRepository
import com.jin.sunflower.core.model.Plant

class PlantRepositoryImpl(
    private val unsplashApi: UnsplashDataSource,
    private val wikipediaApi: WikipediaDataSource
) : PlantRepository {

    override suspend fun loadPlantList(): List<Plant> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlantList(plant: Plant): Plant {
        TODO("Not yet implemented")
    }
}
