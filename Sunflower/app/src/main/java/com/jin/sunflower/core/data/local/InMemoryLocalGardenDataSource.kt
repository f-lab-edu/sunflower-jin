package com.jin.sunflower.core.data.local

import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class InMemoryLocalGardenDataSource {
    private val _myGardenChannel = Channel<List<Plant>>(Channel.BUFFERED)
    val myGardenFlow = _myGardenChannel.receiveAsFlow() // 변경된 부분을 감지하여 전달.

    private val myGardenList = mutableListOf<Plant>()

    suspend fun addedPlantToGarden(plant: Plant) {
        myGardenList.add(plant) // list 에 저장하고,
        _myGardenChannel.send(myGardenList.toList()) // 저장된 list 를 channel 에 알린다.
    }
}
