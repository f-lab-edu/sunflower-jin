package com.jin.sunflower.feature.mygarden

import androidx.lifecycle.ViewModel
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyGardenViewModel : ViewModel() {
    private val sampleGardenList = listOf(
        Plant(
            id = 1,
            name = "Apple",
            description = "Apple",
            growZoneNumber = 2,
            wateringInterval = 3,
            imageUrl = "Apple Image Url",
            addTimestamp = System.currentTimeMillis(),
            lastWateredTimestamp = System.currentTimeMillis(),
        )
    )

    private val _myGardenList = MutableStateFlow(sampleGardenList)
    val myGardenList: StateFlow<List<Plant>> = _myGardenList
}
