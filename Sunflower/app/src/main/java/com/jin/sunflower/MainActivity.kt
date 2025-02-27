package com.jin.sunflower

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jin.sunflower.feature.Screens
import com.jin.sunflower.feature.main.MainScreen
import com.jin.sunflower.feature.mygarden.MyGardenScreen
import com.jin.sunflower.feature.plantdetail.PlantDetailScreen
import com.jin.sunflower.feature.plantlist.PlantListScreen
import com.jin.sunflower.ui.theme.SunflowerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SunflowerTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.MAIN_SCREEN.route) {
        composable(Screens.MAIN_SCREEN.route) { MainScreen(navController) }
        composable(Screens.MY_GARDEN_SCREEN.route) { MyGardenScreen(navController) }
        composable(Screens.PLANT_LIST_SCREEN.route) { PlantListScreen(navController) }
        composable(Screens.PLANT_DETAIL_SCREEN.route) { PlantDetailScreen(navController) }
    }
}
