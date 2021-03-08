package com.lollipop.weatherapp.data.db

import android.content.Context
import androidx.room.*
import com.lollipop.weatherapp.data.db.entity.WeatherConverter
import com.lollipop.weatherapp.data.db.entity.WeatherResponse

@Database(
    entities = [WeatherResponse::class],
    version = 13
)
@TypeConverters(
    WeatherConverter::class
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object{
        @Volatile private var instance: WeatherDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            WeatherDatabase::class.java, "weatherapp.db")
                .build()
    }
}