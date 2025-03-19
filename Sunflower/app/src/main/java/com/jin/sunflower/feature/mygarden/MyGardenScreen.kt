package com.jin.sunflower.feature.mygarden

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.jin.sunflower.core.data.local.InMemoryLocalGardenDataSource
import com.jin.sunflower.core.extensions.formatAsDate
import com.jin.sunflower.core.model.Plant
import com.jin.sunflower.ui.theme.SunflowerTheme
import java.time.Instant

@Composable
fun MyGardenScreen(
    navController: NavController,
    localDataSource: InMemoryLocalGardenDataSource,
    viewModel: MyGardenViewModel = viewModel(
        factory = MyGardenViewModel.createFactory(
            localDataSource
        )
    ),
    onItemClick: (Plant) -> Unit,
) {
    val myGardenList by viewModel.myGardenList.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(innerPadding)
        ) {
            items(myGardenList) { plant ->
                GardenListItem(plant) { onItemClick(it) }
            }
        }
    }
}

@Composable
fun GardenListItem(plant: Plant, onItemClick: (Plant) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
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
                    .height(120.dp),
                model = plant.imageUrl,
                contentDescription = "plant Image",
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = plant.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(Modifier.height(12.dp))

            // 추가된 날짜
            Text(
                text = "Planted",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = plant.addedAt.formatAsDate(),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(12.dp))

            // 마지막 물 준 날짜 & 물 주기
            Text(
                text = "Last Watered",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = plant.lastWateredAt.formatAsDate(),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )
            Text(
                text = "Water in ${plant.wateringIntervalInDays} days.",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true, heightDp = 320)
@Composable
fun MyGardenScreenPreview() {
    SunflowerTheme {
        MyGardenScreen(rememberNavController(), InMemoryLocalGardenDataSource(), onItemClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun GardenListItemPreview() {
    SunflowerTheme {
        GardenListItem(
            Plant(
                name = "Apple",
                description = "Apple",
                growZoneNumber = 2713,
                wateringIntervalInDays = 3073,
                imageUrl = "https://picsum.photos/300/200?random=1",
                addedAt = Instant.now(),
                lastWateredAt = Instant.now(),
            ),
            {}
        )
    }
}
