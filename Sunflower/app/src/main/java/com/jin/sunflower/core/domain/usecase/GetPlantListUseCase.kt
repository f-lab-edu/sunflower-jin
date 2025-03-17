package com.jin.sunflower.core.domain.usecase

import com.jin.sunflower.core.domain.repository.PlantRepository
import com.jin.sunflower.core.model.Plant

class GetPlantListUseCase(private val plantRepository: PlantRepository) {
    suspend operator fun invoke(plant: Plant): Plant {
        return plantRepository.fetchPlantData(plant)
    }
}
