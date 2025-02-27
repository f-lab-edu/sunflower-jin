package com.jin.sunflower.feature.plantdetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jin.sunflower.ui.theme.SunflowerTheme

@Composable
fun PlantDetailScreen(navController: NavController, viewModel: PlantDetailViewModel = viewModel()) {

}

@Preview
@Composable
fun PlantDetailScreenPreview() {
    SunflowerTheme {
        PlantDetailScreen(rememberNavController())
    }
}