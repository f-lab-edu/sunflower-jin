package com.jin.sunflower.core.data.repository

import com.jin.sunflower.core.data.local.InMemoryLocalPlantDataSource
import com.jin.sunflower.core.domain.repository.PlantRepository
import com.jin.sunflower.core.model.Plant

class PlantRepositoryImpl(
    private val localDataSource: InMemoryLocalPlantDataSource
) : PlantRepository {

    override suspend fun loadPlantList(): List<Plant> {
        return localDataSource.loadPlantList().value
    }
}
