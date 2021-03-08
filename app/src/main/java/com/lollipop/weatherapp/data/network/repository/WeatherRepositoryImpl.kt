package com.lollipop.weatherapp.data.network.repository

import androidx.lifecycle.LiveData
import com.lollipop.weatherapp.data.db.WeatherDao
import com.lollipop.weatherapp.data.network.WeatherNetworkDataSource
import com.lollipop.weatherapp.data.db.entity.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class WeatherRepositoryImpl(
    private val weatherDao: WeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : WeatherRepository {

    init {
        weatherNetworkDataSource.downloadedWeather.observeForever { newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(): LiveData<WeatherResponse> {
        return withContext(Dispatchers.IO){
            initWeather()
            return@withContext weatherDao.getWeatherData()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: WeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            weatherDao.upsert(fetchedWeather)
        }
    }

    private suspend fun initWeather(){
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchWeather(
            "-1.2331762",
            "116.8866565",
            "metric"
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}