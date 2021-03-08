package com.lollipop.weatherapp.data.network.repository

import androidx.lifecycle.LiveData
import com.lollipop.weatherapp.data.db.entity.WeatherResponse

interface WeatherRepository {
    suspend fun getCurrentWeather(): LiveData<WeatherResponse>
}