package com.lollipop.weatherapp.data.db.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coord(
    val lat: Double,
    val lon: Double
)