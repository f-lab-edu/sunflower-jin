package com.jin.sunflower.feature.plantdetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jin.sunflower.ui.theme.SunflowerTheme

@Composable
fun PlantDetailScreen(navController: NavController) {

}

@Preview
@Composable
fun PlantDetailScreenPreview() {
    SunflowerTheme {
        PlantDetailScreen(rememberNavController())
    }
}