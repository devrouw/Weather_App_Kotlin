package com.lollipop.weatherapp.data.db.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "feels_like")
    val feelsLike: Double,
    val humidity: Double,
    val pressure: Double,
    val temp: Double,
    @Json(name = "temp_max")
    val tempMax: Double,
    @Json(name = "temp_min")
    val tempMin: Double
)