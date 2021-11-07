package com.olegdev.weatherapp.db

import androidx.room.*
import com.olegdev.weatherapp.models.CityModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CityDao {

    @Query("SELECT * FROM citymodel")
    fun getCities(): Single<List<CityModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCity(cityModel: CityModel) : Completable

    @Query("SELECT * FROM citymodel WHERE city = (:city)")
    fun getCity(city: String): Single<CityModel>

    @Delete
    fun deleteCity(city: CityModel) : Completable

}
