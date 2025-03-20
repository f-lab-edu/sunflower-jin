package com.jin.sunflower.core.data.repository

import com.jin.sunflower.core.data.local.InMemoryLocalGardenDataSource
import com.jin.sunflower.core.domain.repository.GardenRepository
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.Flow

class GardenRepositoryImpl(private val localDataSource: InMemoryLocalGardenDataSource) :
    GardenRepository {

    override suspend fun addedPlantToMyGardenList(plant: Plant) {
        localDataSource.addedPlantToGarden(plant)
    }

    override fun loadMyGardenList(): Flow<List<Plant>> {
        return localDataSource.myGardenFlow
    }
}
