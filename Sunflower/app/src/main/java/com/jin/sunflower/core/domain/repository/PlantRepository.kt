package com.jin.sunflower.core.domain.repository

import com.jin.sunflower.core.model.Plant

interface PlantRepository {
    suspend fun loadPlantList(): List<Plant>
    suspend fun fetchPlantData(plant: Plant): Plant
}
