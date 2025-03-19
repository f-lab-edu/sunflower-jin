package com.jin.sunflower.feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material.icons.rounded.LocalFlorist
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jin.sunflower.core.data.local.InMemoryLocalGardenDataSource
import com.jin.sunflower.core.data.local.InMemoryLocalPlantDataSource
import com.jin.sunflower.core.data.unsplash.UnsplashDataSource
import com.jin.sunflower.core.data.unsplash.UnsplashService
import com.jin.sunflower.core.data.wikipedia.WikipediaDataSource
import com.jin.sunflower.core.data.wikipedia.WikipediaService
import com.jin.sunflower.core.model.Plant
import com.jin.sunflower.feature.mygarden.MyGardenScreen
import com.jin.sunflower.feature.plantlist.PlantListScreen
import com.jin.sunflower.ui.theme.SunflowerTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    localPlantDataSource: InMemoryLocalPlantDataSource,
    localGardenDataSource: InMemoryLocalGardenDataSource,
    viewModel: MainViewModel = viewModel(),
    onItemClick:(Plant) -> Unit
) {
    val pagerState = rememberPagerState { TabMenu.entries.size }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Sunflower",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TabRow(
                selectedTabIndex = pagerState.currentPage
            ) {
                TabMenu.entries.forEachIndexed { index, tabMenu ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = { Text(text = tabMenu.title) },
                        icon = {
                            Icon(
                                imageVector = tabMenu.icon,
                                contentDescription = tabMenu.title
                            )
                        }
                    )
                }
            }

            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
                when (TabMenu.entries[page]) {
                    TabMenu.MY_GARDEN -> MyGardenScreen(
                        navController = navController,
                        localDataSource = localGardenDataSource,
                        onItemClick = onItemClick,
                    )

                    else -> PlantListScreen(
                        navController = navController,
                        localDataSource = localPlantDataSource,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    SunflowerTheme {
        MainScreen(
            rememberNavController(),
            localPlantDataSource = InMemoryLocalPlantDataSource(
                unsplashApi = UnsplashDataSource(apiService = UnsplashService.unsplashApi),
                wikipediaApi = WikipediaDataSource(apiService = WikipediaService.wikipediaService)
            ),
            localGardenDataSource = InMemoryLocalGardenDataSource(),
            onItemClick = {}
        )
    }
}

enum class TabMenu(val title: String, val icon: ImageVector) {
    MY_GARDEN("My Garden", Icons.Rounded.LocalFlorist),
    PLANT_LIST("Plant List", Icons.Rounded.Eco)
}
