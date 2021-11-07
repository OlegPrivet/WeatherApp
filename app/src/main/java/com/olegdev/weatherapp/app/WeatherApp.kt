package com.olegdev.weatherapp.app

import android.app.Application
import com.olegdev.weatherapp.repositories.CitiesRepository
import com.olegdev.weatherapp.repositories.WeatherRepository

/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        WeatherRepository.initialize(context = this)
        CitiesRepository.initialize(context = this)
    }


}