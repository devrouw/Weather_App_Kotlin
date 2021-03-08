package com.lollipop.weatherapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lollipop.weatherapp.data.network.repository.FakeWeatherRepository
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        viewModel = HomeViewModel(FakeWeatherRepository())
    }

    @Test
    fun getWeather() {
        viewModel.weather
    }
}