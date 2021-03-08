package com.lollipop.weatherapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lollipop.weatherapp.data.network.repository.WeatherRepository

class HomeViewModelFactory(
    private val weatherRepository: WeatherRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(weatherRepository) as T
    }
}