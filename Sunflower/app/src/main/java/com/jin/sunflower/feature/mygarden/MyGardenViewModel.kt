package com.jin.sunflower.feature.mygarden

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jin.sunflower.core.domain.usecase.GetMyGardenListUseCase
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyGardenViewModel(private val getMyGardenListUseCase: GetMyGardenListUseCase) : ViewModel() {

    private val _myGardenList = MutableStateFlow<List<Plant>>(emptyList())
    val myGardenList: StateFlow<List<Plant>> = _myGardenList

    init {
        viewModelScope.launch {
            getMyGardenListUseCase().collect { plants ->
                _myGardenList.value = plants
            }
        }
    }
}
