package com.lollipop.weatherapp.data.db.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Clouds(
    val all: Double
)