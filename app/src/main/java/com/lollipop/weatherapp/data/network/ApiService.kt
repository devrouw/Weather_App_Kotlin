package com.lollipop.weatherapp.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.lollipop.weatherapp.BuildConfig
import com.lollipop.weatherapp.data.db.entity.WeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    fun getWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("units") units: String
    ): Deferred<WeatherResponse>
    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : ApiService{
            val requestInterceptor = Interceptor {
                chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("appid",BuildConfig.API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}