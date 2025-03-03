package com.jin.sunflower.feature.plantlist

import androidx.lifecycle.ViewModel
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlantListViewModel : ViewModel() {
    private val samplePlantList = listOf(
        Plant(
            id = 1,
            name = "Apple",
            description = "Apple",
            growZoneNumber = 1,
            wateringInterval = 1,
            imageUrl = "https://picsum.photos/300/200?random=1",
            addTimestamp = System.currentTimeMillis(),
            lastWateredTimestamp = System.currentTimeMillis(),
        ),
        Plant(
            id = 2,
            name = "Beet",
            description = "Beet",
            growZoneNumber = 1,
            wateringInterval = 1,
            imageUrl = "https://picsum.photos/300/200?random=1",
            addTimestamp = System.currentTimeMillis(),
            lastWateredTimestamp = System.currentTimeMillis(),
        ),
        Plant(
            id = 3,
            name = "Cilantro",
            description = "Cilantro",
            growZoneNumber = 1,
            wateringInterval = 1,
            imageUrl = "https://picsum.photos/300/200?random=1",
            addTimestamp = System.currentTimeMillis(),
            lastWateredTimestamp = System.currentTimeMillis(),
        )
    )

    private val _plantList = MutableStateFlow(samplePlantList)
    val plantList: StateFlow<List<Plant>> = _plantList.asStateFlow()
}
