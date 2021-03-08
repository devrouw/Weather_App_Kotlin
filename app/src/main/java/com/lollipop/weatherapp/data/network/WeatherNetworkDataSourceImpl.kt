package com.lollipop.weatherapp.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lollipop.weatherapp.internal.NoConnectivityException
import com.lollipop.weatherapp.data.db.entity.WeatherResponse
import timber.log.Timber

class WeatherNetworkDataSourceImpl(
    private val apiService: ApiService
) : WeatherNetworkDataSource {
    private val _downloadedWeatherData = MutableLiveData<WeatherResponse>()
    override val downloadedWeather: LiveData<WeatherResponse>
        get() = _downloadedWeatherData

    override suspend fun fetchWeather(latitude: String, longitude: String, units: String) {
        try {
            val fetchedWeather = apiService
                .getWeather(latitude,longitude,units)
                .await()
            _downloadedWeatherData.postValue(fetchedWeather)
        }
        catch (e: NoConnectivityException){
            Timber.e("No Internet Connection.")
        }
    }
}