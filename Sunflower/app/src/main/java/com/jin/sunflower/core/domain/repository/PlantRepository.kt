package com.jin.sunflower.core.domain.repository

import com.jin.sunflower.core.model.Plant

interface PlantRepository {
    suspend fun getPlantList(plant: Plant): Plant
    suspend fun loadPlantList(): List<Plant>
}
