package com.jin.sunflower.feature.plantdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.jin.sunflower.core.data.local.InMemoryLocalGardenDataSource
import com.jin.sunflower.core.data.repository.GardenRepositoryImpl
import com.jin.sunflower.core.domain.usecase.SaveMyGardenListUseCase
import com.jin.sunflower.core.model.Plant

class PlantDetailViewModel(
    private val saveMyGardenListUseCase: SaveMyGardenListUseCase
) : ViewModel() {

    fun addedPlant(plant: Plant) {
        saveMyGardenListUseCase(plant)
    }

    companion object {
        fun createFactory(localGardenDataSource: InMemoryLocalGardenDataSource): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>, extras: CreationExtras
                ): T {
                    require(modelClass.isAssignableFrom(PlantDetailViewModel::class.java)) {
                        "Unknown ViewModel class: ${modelClass.name}"
                    }
                    @Suppress("UNCHECKED_CAST") return PlantDetailViewModel(
                        saveMyGardenListUseCase = SaveMyGardenListUseCase(
                            GardenRepositoryImpl(localGardenDataSource)
                        )

                    ) as T
                }
            }
        }
    }
}
