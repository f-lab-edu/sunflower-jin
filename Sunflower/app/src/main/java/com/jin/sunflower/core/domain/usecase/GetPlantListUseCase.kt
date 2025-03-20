package com.jin.sunflower.core.domain.usecase

import com.jin.sunflower.core.domain.repository.PlantRepository
import com.jin.sunflower.core.model.Plant

class GetPlantListUseCase(private val repository: PlantRepository) {
    suspend operator fun invoke(): List<Plant> {
        return repository.loadPlantList()
    }
}
