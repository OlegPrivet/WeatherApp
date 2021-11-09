package com.olegdev.weatherapp.repositories

import com.olegdev.weatherapp.db.CityDataBase
import com.olegdev.weatherapp.models.CityModel
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/
class CitiesRepository @Inject constructor(database: CityDataBase)  {

    private val cityDao = database.cityDao()

    fun getCities() : Single<List<CityModel>> = cityDao.getCities()
    fun getCity(city: String) : Single<CityModel> = cityDao.getCity(city = city)

    fun saveCity(city: CityModel) : Completable {
        return cityDao.saveCity(city)
    }

    fun deleteCity(city: CityModel) : Completable {
        return cityDao.deleteCity(city)
    }
}