package com.jin.sunflower.core.domain.usecase

import com.jin.sunflower.core.domain.repository.GardenRepository
import com.jin.sunflower.core.model.Plant

class SaveMyGardenListUseCase(private val repository: GardenRepository) {
    suspend operator fun invoke(plant: Plant) {
        return repository.addedPlantToMyGardenList(plant)
    }
}
