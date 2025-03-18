package com.jin.sunflower.feature.mygarden

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.jin.sunflower.core.data.local.InMemoryLocalGardenDataSource
import com.jin.sunflower.core.data.repository.GardenRepositoryImpl
import com.jin.sunflower.core.domain.usecase.GetMyGardenListUseCase
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MyGardenViewModel(private val getMyGardenListUseCase: GetMyGardenListUseCase) : ViewModel() {

    val myGardenList: StateFlow<List<Plant>> =
        getMyGardenListUseCase().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    companion object {
        fun createFactory(localGardenDataSource: InMemoryLocalGardenDataSource): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    require(modelClass.isAssignableFrom(MyGardenViewModel::class.java)) {
                        "Unknown ViewModel class: ${modelClass.name}"
                    }
                    @Suppress("UNCHECKED_CAST")
                    return MyGardenViewModel(
                        getMyGardenListUseCase = GetMyGardenListUseCase(
                            repository = GardenRepositoryImpl(localGardenDataSource)
                        )
                    ) as T
                }
            }
        }
    }
}
