package com.jin.sunflower.feature.plantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.jin.sunflower.core.data.di.DataFactory
import com.jin.sunflower.core.data.repository.PlantRepository
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant

class PlantListViewModel(private val repository: PlantRepository) : ViewModel() {

    private val _plantList = MutableStateFlow<List<Plant>>(emptyList())
    val plantList: StateFlow<List<Plant>> = _plantList.asStateFlow()

    fun loadPlantList() {
        viewModelScope.launch {
            val imageUrl = repository.fetchImageUrl()
            _plantList.value = listOf(
                Plant(
                    id = 1,
                    name = "Apple",
                    description = "Apple",
                    growZoneNumber = 1,
                    wateringIntervalInDays = 1,
                    imageUrl = imageUrl,
                    addedAt = Instant.now(),
                    lastWateredAt = Instant.now(),
                ),
                Plant(
                    id = 2,
                    name = "Beet",
                    description = "Beet",
                    growZoneNumber = 1,
                    wateringIntervalInDays = 1,
                    imageUrl = imageUrl,
                    addedAt = Instant.now(),
                    lastWateredAt = Instant.now(),
                ),
                Plant(
                    id = 3,
                    name = "Cilantro",
                    description = "Cilantro",
                    growZoneNumber = 1,
                    wateringIntervalInDays = 1,
                    imageUrl = imageUrl,
                    addedAt = Instant.now(),
                    lastWateredAt = Instant.now(),
                )
            )

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                if (modelClass.isAssignableFrom(PlantListViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST") return PlantListViewModel(DataFactory.providePlantsRepository()) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
