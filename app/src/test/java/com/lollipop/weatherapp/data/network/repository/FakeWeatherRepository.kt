package com.lollipop.weatherapp.data.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lollipop.weatherapp.data.db.entity.WeatherResponse

class FakeWeatherRepository: WeatherRepository {

    private val observableWeatherData = MutableLiveData<WeatherResponse>()

    override suspend fun getCurrentWeather(): LiveData<WeatherResponse> {
        return observableWeatherData
    }


}