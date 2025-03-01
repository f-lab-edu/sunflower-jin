package com.jin.sunflower.feature.plantdetail

import androidx.lifecycle.ViewModel
import com.jin.sunflower.core.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlantDetailViewModel : ViewModel() {
    private val samplePlantDetail = Plant(
        id = 1,
        name = "Apple",
        description = "An apple is a sweet, edible fruit produced by an apple tree (Malus pumila). Apple trees are cultivated worldwide, and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe, and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek and European Christian traditions.\n" +
                "Apple trees are large if grown from seed.\n" +
                "Generally apple cultivars are propagated by grafting onto rootstocks, which control the size of the resulting tree. There are more than 7,500 known cultivars of apples, resulting in a range of desired\n" +
                "characteristics. Different cultivars are bred for various tastes and uses, including cooking, eating raw and cider production.\n" +
                "Trees and fruit are prone to a number of fungal, bacterial and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.\n" +
                "Worldwide production of apples in 2014 was 84.6 million tonnes, with China accounting for 48% of the total.",
        growZoneNumber = 1,
        wateringInterval = 30,
        imageUrl = "Apple Image Url"
    )

    private val _plantDetail = MutableStateFlow(samplePlantDetail)
    val plantDetail: StateFlow<Plant> = _plantDetail
}
