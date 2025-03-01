package com.jin.sunflower.core.model

data class Plant(
    val id: Int,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int,
    val imageUrl: String = ""
)
