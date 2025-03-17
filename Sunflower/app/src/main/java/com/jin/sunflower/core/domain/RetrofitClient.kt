package com.jin.sunflower.core.domain

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val gson: Gson = GsonBuilder().create()

    fun <T> createService(baseUrl: String, service: Class<T>): T {
        val retrofitBuild = buildRetrofit(baseUrl)
        return retrofitBuild.create(service)
    }

    private fun buildRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
