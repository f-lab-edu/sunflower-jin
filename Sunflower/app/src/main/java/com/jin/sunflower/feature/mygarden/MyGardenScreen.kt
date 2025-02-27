package com.jin.sunflower.feature.mygarden

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jin.sunflower.ui.theme.SunflowerTheme

@Composable
fun MyGardenScreen(navController: NavController, viewModel: MyGardenViewModel = viewModel()) {

}

@Preview
@Composable
fun MyGardenScreenPreview() {
    SunflowerTheme {
        MyGardenScreen(rememberNavController())
    }
}