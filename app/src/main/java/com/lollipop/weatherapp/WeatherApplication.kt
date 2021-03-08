package com.lollipop.weatherapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.lollipop.weatherapp.data.db.WeatherDatabase
import com.lollipop.weatherapp.data.network.*
import com.lollipop.weatherapp.data.network.repository.WeatherRepository
import com.lollipop.weatherapp.data.network.repository.WeatherRepositoryImpl
import com.lollipop.weatherapp.ui.home.HomeViewModelFactory
import dagger.hilt.android.HiltAndroidApp
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

@HiltAndroidApp
class WeatherApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@WeatherApplication))

        bind() from singleton { WeatherDatabase(instance()) }
        bind() from singleton { instance<WeatherDatabase>().weatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<WeatherRepository>() with singleton { WeatherRepositoryImpl(instance(), instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}