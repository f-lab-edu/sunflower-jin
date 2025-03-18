package com.jin.sunflower.feature.plantdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jin.sunflower.core.data.local.InMemoryLocalGardenDataSource
import com.jin.sunflower.core.model.Plant
import com.jin.sunflower.ui.theme.SunflowerTheme
import java.time.Instant

@Composable
fun PlantDetailScreen(
    navController: NavController,
    plantDetail: Plant,
    localDataSource: InMemoryLocalGardenDataSource,
    viewModel: PlantDetailViewModel = viewModel(
        factory = PlantDetailViewModel.createFactory(
            localDataSource
        )
    )
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // 세로 스크롤 가능하게 만듦
                .padding(bottom = 60.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = plantDetail.imageUrl,
                    contentDescription = "plant detail image",
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "뒤로가기",
                            tint = Color.White,
                            modifier = Modifier
                                .size(36.dp)
                                .padding(start = 10.dp)
                        )
                    }
                    Spacer(modifier = Modifier)
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "공유하기",
                            tint = Color.White,
                            modifier = Modifier
                                .size(36.dp)
                                .padding(end = 10.dp)
                        )
                    }
                }
            }
            IconButton(onClick = { viewModel.addedPlant(plantDetail) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "추가하기",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 10.dp)
                )
            }

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
}

@Preview(showBackground = true)
@Composable
fun PlantDetailScreenPreview() {
    SunflowerTheme {
        PlantDetailScreen(
            rememberNavController(), Plant(
                name = "Colleen Chambers",
                description = "placerat",
                growZoneNumber = 1441,
                wateringIntervalInDays = 4274,
                imageUrl = "https://www.google.com/#q=inciderint",
                addedAt = Instant.now(),
                lastWateredAt = Instant.now()
            ),
            InMemoryLocalGardenDataSource()
        )
    }
}
