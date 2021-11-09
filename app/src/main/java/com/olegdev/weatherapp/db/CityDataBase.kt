package com.olegdev.weatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.olegdev.weatherapp.models.CityModel


/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/
@Database(entities = [CityModel::class], version = 1)
abstract class CityDataBase: RoomDatabase() {

    abstract fun cityDao(): CityDao
}

