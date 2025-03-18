package com.jin.sunflower.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.Instant

@Parcelize
data class Plant(
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringIntervalInDays: Int,
    val imageUrl: String,
    val addedAt: Instant,
    var lastWateredAt: Instant
) : Parcelable
