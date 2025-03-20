package com.jin.sunflower.core.domain.repository

import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.Flow

interface GardenRepository {
    suspend fun addPlantToMyGarden(plant: Plant)
    fun loadMyGardenList(): Flow<List<Plant>>
}
