package com.jin.sunflower.feature.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jin.sunflower.feature.Screens
import com.jin.sunflower.ui.theme.SunflowerTheme

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel = viewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Row(modifier = Modifier.padding(innerPadding)) {
            Button(onClick = {
                navController.navigate(Screens.MyGardenScreen.route)
            }) {
                Text("My garden")
            }

            Button(onClick = {
                navController.navigate(Screens.PlantListScreen.route)
            }) {
                Text("Plant list")
            }

            Button(onClick = {
                navController.navigate(Screens.PlantDetailScreen.route)
            }) {
                Text("Plant Detail")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    SunflowerTheme {
        MainScreen(rememberNavController())
    }
}
