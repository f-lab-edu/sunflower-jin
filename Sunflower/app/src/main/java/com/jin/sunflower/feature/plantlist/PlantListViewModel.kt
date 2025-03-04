package com.jin.sunflower.feature.plantlist

import androidx.lifecycle.ViewModel
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant

class PlantListViewModel : ViewModel() {
    private val samplePlantList = listOf(
        Plant(
            id = 1,
            name = "Apple",
            description = "Apple",
            growZoneNumber = 1,
            wateringIntervalInDays = 1,
            imageUrl = "https://picsum.photos/300/200?random=1",
            addedAt = Instant.now(),
            lastWateredAt = Instant.now(),
        ),
        Plant(
            id = 2,
            name = "Beet",
            description = "Beet",
            growZoneNumber = 1,
            wateringIntervalInDays = 1,
            imageUrl = "https://picsum.photos/300/200?random=1",
            addedAt = Instant.now(),
            lastWateredAt = Instant.now(),
        ),
        Plant(
            id = 3,
            name = "Cilantro",
            description = "Cilantro",
            growZoneNumber = 1,
            wateringIntervalInDays = 1,
            imageUrl = "https://picsum.photos/300/200?random=1",
            addedAt = Instant.now(),
            lastWateredAt = Instant.now(),
        )
    )

    private val _plantList = MutableStateFlow(samplePlantList)
    val plantList: StateFlow<List<Plant>> = _plantList.asStateFlow()
}
