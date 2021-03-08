package com.lollipop.weatherapp.data.network

import androidx.lifecycle.LiveData
import com.lollipop.weatherapp.data.db.entity.WeatherResponse

interface WeatherNetworkDataSource {
    val downloadedWeather: LiveData<WeatherResponse>

    suspend fun fetchWeather(
        latitude: String,
        longitude: String,
        units: String
    )
}