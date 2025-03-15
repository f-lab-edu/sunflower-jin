package com.jin.sunflower.core.data.di

import com.jin.sunflower.core.data.repository.PlantRepository
import com.jin.sunflower.core.data.repository.PlantRepositoryImpl
import com.jin.sunflower.core.network.RetrofitInstance

object DataFactory {
    fun providePlantsRepository(): PlantRepository {
        return PlantRepositoryImpl(
            apiService = RetrofitInstance.unsplashApi,
        )
    }
}
