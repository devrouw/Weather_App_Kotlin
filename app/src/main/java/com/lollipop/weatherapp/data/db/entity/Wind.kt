package com.lollipop.weatherapp.data.db.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wind(
    val deg: Double,
    val speed: Double
)