package com.jin.sunflower.feature.plantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.jin.sunflower.core.data.local.InMemoryLocalPlantDataSource
import com.jin.sunflower.core.data.repository.PlantRepositoryImpl
import com.jin.sunflower.core.domain.usecase.GetPlantListUseCase
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlantListViewModel(private val getPlantListUseCase: GetPlantListUseCase) : ViewModel() {

    private val _plantList = MutableStateFlow(emptyList<Plant>())
    val plantList: StateFlow<List<Plant>> = _plantList

    fun loadPlantList() {
        viewModelScope.launch {
            _plantList.value = getPlantListUseCase()
        }
    }

    companion object {
        fun createFactory(localPlantDataSource: InMemoryLocalPlantDataSource): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    require(modelClass.isAssignableFrom(PlantListViewModel::class.java)) {
                        "Unknown ViewModel class: ${modelClass.name}"
                    }
                    @Suppress("UNCHECKED_CAST")
                    return PlantListViewModel(
                        getPlantListUseCase = GetPlantListUseCase(
                            PlantRepositoryImpl(
                                localDataSource = localPlantDataSource
                            )
                        )
                    ) as T
                }
            }
        }
    }
}
