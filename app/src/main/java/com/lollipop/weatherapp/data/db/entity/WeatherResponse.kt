package com.lollipop.weatherapp.data.db.entity

import androidx.room.*
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

@Entity(tableName = "tb_weather")
@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val base: String,
    @Embedded(prefix = "clouds_")
    val clouds: Clouds,
    val cod: Double,
    @Embedded(prefix = "coord_")
    val coord: Coord,
    val dt: Double,
    @Embedded(prefix = "main_")
    val main: Main,
    val name: String,
    @Embedded(prefix = "sys_")
    val sys: Sys,
    val timezone: Double,
    val visibility: Int,
    val weather: List<Weather>,
    @Embedded(prefix = "wind_")
    val wind: Wind,
    val dateNow: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy"))
){
    @PrimaryKey(autoGenerate = false)
    var ids: Int = 0
}

class WeatherConverter {
    @TypeConverter
    fun listToJson(value: List<Weather>?): String{
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Weather::class.java)
        val adapter = moshi.adapter<List<Weather>>(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Weather> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Weather::class.java)
        val adapter = moshi.adapter<List<Weather>>(type)
        return adapter.fromJson(value)!!
    }
}