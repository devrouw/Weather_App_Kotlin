package com.lollipop.weatherapp.ui.home

import androidx.lifecycle.ViewModel
import com.lollipop.weatherapp.data.network.repository.WeatherRepository
import com.lollipop.weatherapp.internal.lazyDeferred

class HomeViewModel (
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    val weather by lazyDeferred {
        weatherRepository.getCurrentWeather()
    }
}