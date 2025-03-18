package com.jin.sunflower.core.model

import java.time.Instant

data class Plant(
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringIntervalInDays: Int,
    val imageUrl: String,
    val addedAt: Instant,
    var lastWateredAt: Instant
)
