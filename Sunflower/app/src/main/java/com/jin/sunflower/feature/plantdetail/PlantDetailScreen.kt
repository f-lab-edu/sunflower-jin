package com.jin.sunflower.feature.plantdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jin.sunflower.ui.theme.SunflowerTheme

@Composable
fun PlantDetailScreen(navController: NavController, viewModel: PlantDetailViewModel = viewModel()) {
    val plantDetail by viewModel.plantDetail.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // 세로 스크롤 가능하게 만듦
            .padding(bottom = 60.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            model = plantDetail.imageUrl,
            contentDescription = "plant detail image",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = plantDetail.name,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
        // 물 주어야 하는 주기
        Text(
            text = "Watering needs",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "every ${plantDetail.wateringIntervalInDays} days",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = plantDetail.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PlantDetailScreenPreview() {
    SunflowerTheme {
        PlantDetailScreen(rememberNavController())
    }
}
