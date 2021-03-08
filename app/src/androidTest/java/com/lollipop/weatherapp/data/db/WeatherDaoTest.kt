package com.lollipop.weatherapp.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import com.lollipop.weatherapp.data.db.entity.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
@SmallTest
class WeatherDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: WeatherDatabase
    private lateinit var dao: WeatherDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WeatherDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.weatherDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun writeAndReadWeather(){
        val date = Date()
        val weather = WeatherResponse("10", Clouds(10.0),10.0,
            Coord(10.0,10.0),10.0, Main(10.0,10.0,10.0,10.0,10.0,10.0),"Name",
            Sys("Indonesia",10.0,10.0),10.0,10, listOf(Weather("desc","icon","main")),
            Wind(10.0,10.0),date.toString())
        dao.upsert(weather)
        val data = dao.getWeatherData().getOrAwaitValue()
        assertThat(data).isEqualTo(data)
    }
}