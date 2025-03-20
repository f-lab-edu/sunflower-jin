package com.jin.sunflower.core.domain.usecase

import com.jin.sunflower.core.domain.repository.GardenRepository
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.Flow

class GetMyGardenListUseCase(private val repository: GardenRepository) {
    operator fun invoke(): Flow<List<Plant>> {
        return repository.loadMyGardenList()
    }
}
