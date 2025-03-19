package com.jin.sunflower

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jin.sunflower.core.data.local.InMemoryLocalGardenDataSource
import com.jin.sunflower.core.data.local.InMemoryLocalPlantDataSource
import com.jin.sunflower.core.data.repository.GardenRepositoryImpl
import com.jin.sunflower.core.data.repository.PlantRepositoryImpl
import com.jin.sunflower.core.data.unsplash.UnsplashDataSource
import com.jin.sunflower.core.data.unsplash.UnsplashService
import com.jin.sunflower.core.data.wikipedia.WikipediaDataSource
import com.jin.sunflower.core.data.wikipedia.WikipediaService
import com.jin.sunflower.core.domain.usecase.GetMyGardenListUseCase
import com.jin.sunflower.core.domain.usecase.GetPlantListUseCase
import com.jin.sunflower.core.domain.usecase.SaveMyGardenListUseCase
import com.jin.sunflower.core.model.Plant
import com.jin.sunflower.feature.Screens
import com.jin.sunflower.feature.main.MainScreen
import com.jin.sunflower.feature.mygarden.MyGardenScreen
import com.jin.sunflower.feature.mygarden.MyGardenViewModel
import com.jin.sunflower.feature.plantdetail.PlantDetailScreen
import com.jin.sunflower.feature.plantdetail.PlantDetailViewModel
import com.jin.sunflower.feature.plantlist.PlantListScreen
import com.jin.sunflower.feature.plantlist.PlantListViewModel
import com.jin.sunflower.ui.theme.SunflowerTheme

class MainActivity : ComponentActivity() {
    private val localPlantDataSource: InMemoryLocalPlantDataSource by lazy {
        InMemoryLocalPlantDataSource(
            UnsplashDataSource(UnsplashService.unsplashApi),
            WikipediaDataSource(WikipediaService.wikipediaService)
        )
    }
    private val localGardenDataSource: InMemoryLocalGardenDataSource by lazy {
        InMemoryLocalGardenDataSource()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SunflowerTheme {
                AppNavigator(localPlantDataSource, localGardenDataSource)
            }
        }
    }
}

@Composable
fun AppNavigator(
    localPlantDataSource: InMemoryLocalPlantDataSource,
    localGardenDataSource: InMemoryLocalGardenDataSource
) {
    val navController = rememberNavController()
    val myGardenViewModel =
        MyGardenViewModel(GetMyGardenListUseCase(GardenRepositoryImpl(localGardenDataSource)))
    val plantListViewModel =
        PlantListViewModel(GetPlantListUseCase(PlantRepositoryImpl(localPlantDataSource)))

    NavHost(navController = navController, startDestination = Screens.MAIN_SCREEN.route) {
        composable(Screens.MAIN_SCREEN.route) {
            MainScreen(
                navController,
                myGardenViewModel,
                plantListViewModel,
                onItemClick = navController::goToPlantDetailView
            )
        }
        composable(Screens.MY_GARDEN_SCREEN.route) {
            MyGardenScreen(
                navController,
                myGardenViewModel,
                navController::goToPlantDetailView
            )
        }
        composable(Screens.PLANT_LIST_SCREEN.route) {
            PlantListScreen(
                navController,
                plantListViewModel,
                navController::goToPlantDetailView
            )
        }
        composable(Screens.PLANT_DETAIL_SCREEN.route) {
            val plantDetailViewModel = PlantDetailViewModel(
                SaveMyGardenListUseCase(
                    GardenRepositoryImpl(localGardenDataSource)
                )
            )
            val plant = remember {
                navController.previousBackStackEntry?.savedStateHandle?.get<Plant>("plant")
            } ?: return@composable
            PlantDetailScreen(navController, plant, plantDetailViewModel)
        }
    }
}

fun NavController.goToPlantDetailView(plant: Plant) {
    this.currentBackStackEntry?.savedStateHandle?.set(key = "plant", value = plant)
    this.navigate(Screens.PLANT_DETAIL_SCREEN.route)
}
