package com.lollipop.weatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

//const val WEATHER_ID = 0
//
//@Entity(tableName = "tbl_weather")
@JsonClass(generateAdapter = true)
data class Weather(
    val description: String,
    val icon: String,
    val main: String
)
//{
//    @PrimaryKey(autoGenerate = false)
//    var id: Int = WEATHER_ID
//}