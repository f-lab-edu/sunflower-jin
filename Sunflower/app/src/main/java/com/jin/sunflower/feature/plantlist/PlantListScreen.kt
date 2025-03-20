package com.jin.sunflower.feature.plantlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jin.sunflower.core.data.local.InMemoryLocalPlantDataSource
import com.jin.sunflower.core.data.repository.PlantRepositoryImpl
import com.jin.sunflower.core.data.unsplash.UnsplashDataSource
import com.jin.sunflower.core.data.unsplash.UnsplashService
import com.jin.sunflower.core.data.wikipedia.WikipediaDataSource
import com.jin.sunflower.core.data.wikipedia.WikipediaService
import com.jin.sunflower.core.domain.usecase.GetPlantListUseCase
import com.jin.sunflower.core.model.Plant
import com.jin.sunflower.ui.theme.SunflowerTheme
import java.time.Instant

@Composable
fun PlantListScreen(
    navController: NavController,
    viewModel: PlantListViewModel,
    onItemClick: (Plant) -> Unit
) {
    val plantList by viewModel.plantList.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(innerPadding)
        ) {
            items(plantList) { plant ->
                PlantListItem(plant) { onItemClick(it) }
            }
        }
    }

}

@Composable
fun PlantListItem(plant: Plant, onItemClick: (Plant) -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = { onItemClick(plant) }),
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFd8e7cb),
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                model = plant.imageUrl,
                contentDescription = "${plant.name} image",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = plant.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true, heightDp = 320)
@Composable
fun PlantListScreenPreview() {
    SunflowerTheme {
        PlantListScreen(
            rememberNavController(),
            PlantListViewModel(
                GetPlantListUseCase(
                    PlantRepositoryImpl(
                        InMemoryLocalPlantDataSource(
                            UnsplashDataSource(UnsplashService.unsplashApi),
                            WikipediaDataSource(WikipediaService.wikipediaService)
                        )
                    )
                )
            ),
            {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlantListItemPreview() {
    SunflowerTheme {
        PlantListItem(
            Plant(
                name = "Apple",
                description = "Apple",
                growZoneNumber = 2713,
                wateringIntervalInDays = 3073,
                imageUrl = "Apple image url",
                addedAt = Instant.now(),
                lastWateredAt = Instant.now(),
            ),
            {}
        )
    }
}
