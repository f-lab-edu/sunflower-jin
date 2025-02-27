package com.jin.sunflower.feature.plantlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jin.sunflower.ui.theme.SunflowerTheme

@Composable
fun PlantListScreen(navController: NavController, viewModel: PlantListViewModel = viewModel()) {

}

@Preview
@Composable
fun PlantListScreenPreview() {
    SunflowerTheme {
        PlantListScreen(rememberNavController())
    }
}