package com.jin.sunflower.core.data.local

import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InMemoryLocalGardenDataSource {

    private val _myGardenList = MutableStateFlow(emptyList<Plant>())
    val myGardenList: StateFlow<List<Plant>> = _myGardenList

    fun addedPlantToGarden(plant: Plant) {
        _myGardenList.value += plant
    }
}
