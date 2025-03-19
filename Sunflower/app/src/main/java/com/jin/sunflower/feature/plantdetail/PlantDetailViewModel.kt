package com.jin.sunflower.feature.plantdetail

import androidx.lifecycle.ViewModel
import com.jin.sunflower.core.domain.usecase.SaveMyGardenListUseCase
import com.jin.sunflower.core.model.Plant

class PlantDetailViewModel(
    private val saveMyGardenListUseCase: SaveMyGardenListUseCase
) : ViewModel() {

    fun addedPlant(plant: Plant) {
        saveMyGardenListUseCase(plant)
    }
}
