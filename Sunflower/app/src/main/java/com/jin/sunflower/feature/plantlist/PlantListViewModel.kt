package com.jin.sunflower.feature.plantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jin.sunflower.core.domain.usecase.GetPlantListUseCase
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlantListViewModel(private val getPlantListUseCase: GetPlantListUseCase) : ViewModel() {

    private val _plantList = MutableStateFlow(emptyList<Plant>())
    val plantList: StateFlow<List<Plant>> = _plantList

    init {
        loadPlantList()
    }

    private fun loadPlantList() {
        viewModelScope.launch {
            _plantList.value = getPlantListUseCase()
        }
    }
}
