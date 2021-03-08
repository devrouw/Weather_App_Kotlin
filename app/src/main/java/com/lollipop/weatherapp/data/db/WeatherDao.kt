package com.lollipop.weatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lollipop.weatherapp.data.db.entity.WeatherResponse

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherResponse: WeatherResponse)

    @Query("select * from tb_weather where ids = 0")
    fun getWeatherData(): LiveData<WeatherResponse>
}