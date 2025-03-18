package com.jin.sunflower.feature

sealed class Screens(val route: String) {
    object MainScreen : Screens("mainScreen")
    object MyGardenScreen : Screens("myGardenScreen")
    object PlantListScreen : Screens("plantListScreen")
    object PlantDetailScreen : Screens("plantDetailScreen")
}
