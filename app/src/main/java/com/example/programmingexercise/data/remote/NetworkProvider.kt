package com.example.programmingexercise.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

/**
 * Singleton object to provide network-related components.
 */
object NetworkProvider {

    private val mediaType = MediaType.get("application/json")

    @OptIn(ExperimentalSerializationApi::class)
    private fun providesJson(): Json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    // Lazily initialize Retrofit. This ensures that the instance is created only when needed.
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(providesJson().asConverterFactory(mediaType))
            .build()
    }

    // Provide a single instance of the API service across the app.
    val apiService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}