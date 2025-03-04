package com.jin.sunflower.feature.mygarden

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jin.sunflower.core.extensions.formatAsDate
import com.jin.sunflower.core.model.Plant
import com.jin.sunflower.ui.theme.SunflowerTheme
import java.time.Instant

@Composable
fun MyGardenScreen(navController: NavController, viewModel: MyGardenViewModel = viewModel()) {
    val myGardenList by viewModel.myGardenList.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(innerPadding)
        ) {
            items(myGardenList) { plant ->
                GardenListItem(plant)
            }
        }
    }
}

@Composable
fun GardenListItem(plant: Plant) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            model = plant.imageUrl,
            contentDescription = "plant Image",
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(20.dp))

        Text(text = plant.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(20.dp))
        // 추가된 날짜
        Text(text = "Planted")
        Text(text = plant.addedAt.formatAsDate())

        Spacer(Modifier.height(20.dp))
        // 마지막 물 준날 + 물 주어야 하는 빈도
        Text(text = "Last Watered")
        Text(text = plant.lastWateredAt.formatAsDate())
        Text(text = "water in ${plant.wateringIntervalInDays} days.")
    }
}

@Preview(showBackground = true, heightDp = 320)
@Composable
fun MyGardenScreenPreview() {
    SunflowerTheme {
        MyGardenScreen(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun GardenListItemPreview() {
    SunflowerTheme {
        GardenListItem(
            Plant(
                id = 123,
                name = "Apple",
                description = "Apple",
                growZoneNumber = 2713,
                wateringIntervalInDays = 3073,
                imageUrl = "https://picsum.photos/300/200?random=1",
                addedAt = Instant.now(),
                lastWateredAt = Instant.now(),
            )
        )
    }
}
