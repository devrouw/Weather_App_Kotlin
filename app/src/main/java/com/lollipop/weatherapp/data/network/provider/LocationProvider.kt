package com.lollipop.weatherapp.data.network.provider

interface LocationProvider {
    suspend fun getPreferredLocationString(): String
}