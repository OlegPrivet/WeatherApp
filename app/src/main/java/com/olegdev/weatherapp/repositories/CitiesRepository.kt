package com.olegdev.weatherapp.repositories

import android.content.Context
import androidx.room.Room
import com.olegdev.weatherapp.db.CityDataBase
import com.olegdev.weatherapp.models.CityModel
import io.reactivex.Completable
import io.reactivex.Single


/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/
class CitiesRepository constructor(context: Context)  {

    private val database: CityDataBase = Room.databaseBuilder(
        context.applicationContext,
        CityDataBase::class.java,
        "weather.db"
    ).build()

    private val cityDao = database.cityDao()

    fun getCities() : Single<List<CityModel>> = cityDao.getCities()
    fun getCity(city: String) : Single<CityModel> = cityDao.getCity(city = city)

    fun saveCity(city: CityModel) : Completable {
        return cityDao.saveCity(city)
    }

    fun deleteCity(city: CityModel) : Completable {
        return cityDao.deleteCity(city)
    }

    companion object {
        private var INSTANCE: CitiesRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CitiesRepository(context = context)
            }
        }

        fun get(): CitiesRepository {
            return INSTANCE ?: throw IllegalStateException("CitiesRepository must be initialized")
        }
    }
}