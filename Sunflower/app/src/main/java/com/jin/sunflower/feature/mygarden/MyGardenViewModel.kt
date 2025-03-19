package com.jin.sunflower.feature.mygarden

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jin.sunflower.core.domain.usecase.GetMyGardenListUseCase
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MyGardenViewModel(private val getMyGardenListUseCase: GetMyGardenListUseCase) : ViewModel() {

    val myGardenList: StateFlow<List<Plant>> =
        getMyGardenListUseCase().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
