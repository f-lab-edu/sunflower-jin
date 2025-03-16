package com.jin.sunflower.core.domain.unsplash

import com.google.gson.annotations.SerializedName

data class UnsplashResponse(
    @SerializedName("id") val id: String,
    @SerializedName("urls") val urls: UnsplashUrls
)

data class UnsplashUrls(
    @SerializedName("regular") val regular: String
)
