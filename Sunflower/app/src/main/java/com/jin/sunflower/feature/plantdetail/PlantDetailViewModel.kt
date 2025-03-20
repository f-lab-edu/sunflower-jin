package com.jin.sunflower.feature.plantdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jin.sunflower.core.domain.usecase.SaveMyGardenListUseCase
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.launch

class PlantDetailViewModel(
    private val saveMyGardenListUseCase: SaveMyGardenListUseCase
) : ViewModel() {

    fun addedPlantToMyGarden(plant: Plant) {
        viewModelScope.launch {
            saveMyGardenListUseCase(plant)
        }
    }
}
