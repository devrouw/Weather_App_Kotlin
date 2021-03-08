package com.lollipop.weatherapp.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lollipop.weatherapp.data.db.entity.WeatherResponse
import com.lollipop.weatherapp.databinding.ActivityMainBinding
import com.lollipop.weatherapp.internal.glide.GlideApp
import com.lollipop.weatherapp.ui.base.ScopedActivity
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import java.util.*

class MainActivity : ScopedActivity(), KodeinAware{

    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch{
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(this@MainActivity, Observer {
            if(it == null) return@Observer
            loadView(it)
        })
    }

    private fun loadView(it: WeatherResponse) {
        binding.txtKota.text = it.name
        binding.txtTgl.text = it.dateNow
        binding.txtTemperature.text = it.main.temp.toString()+" \u2103"
        binding.txtLongitude.text = it.coord.lon.toString()
        binding.txtLatitude.text = it.coord.lat.toString()
        GlideApp.with(this@MainActivity).load("http://openweathermap.org/img/w/${it.weather.get(0).icon}.png").into(binding.imgWeather)
        binding.txtStatus.text = it.weather.get(0).main
        binding.txtFeels.text = it.main.feelsLike.toString()
        binding.txtWindSpeed.text = it.wind.speed.toString()
        binding.txtHumidity.text = it.main.humidity.toString()
        binding.txtSunrise.text = it.sys.sunrise.toString()
        binding.txtSunset.text = it.sys.sunset.toString()
        binding.txtPressure.text = it.main.pressure.toString()
    }


}