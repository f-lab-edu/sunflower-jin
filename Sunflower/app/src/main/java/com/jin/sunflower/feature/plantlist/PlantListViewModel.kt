package com.jin.sunflower.feature.plantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.jin.sunflower.core.data.repository.PlantRepositoryImpl
import com.jin.sunflower.core.data.unsplash.UnsplashDataSource
import com.jin.sunflower.core.data.wikipedia.WikipediaDataSource
import com.jin.sunflower.core.domain.unsplash.UnsplashService
import com.jin.sunflower.core.domain.usecase.GetPlantListUseCase
import com.jin.sunflower.core.domain.wikipedia.WikipediaService
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlantListViewModel(private val getPlantListUseCase: GetPlantListUseCase) : ViewModel() {

    private val _plantList = MutableStateFlow(emptyList<Plant>())
    val plantList: StateFlow<List<Plant>> = _plantList.asStateFlow()

    fun loadPlantList() {
        // todo 재정의
    }

    companion object Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass.isAssignableFrom(PlantListViewModel::class.java)) {
                "Unknown ViewModel class: ${modelClass.name}"
            }
            @Suppress("UNCHECKED_CAST")
            return PlantListViewModel(
                getPlantListUseCase = GetPlantListUseCase(
                    PlantRepositoryImpl(
                        unsplashApi = UnsplashDataSource(UnsplashService.unsplashApi),
                        wikipediaApi = WikipediaDataSource(WikipediaService.wikipediaService)
                    )
                )
            ) as T
        }
    }
}
