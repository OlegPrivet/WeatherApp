package com.olegdev.weatherapp.repositories

import com.olegdev.weatherapp.data.remote.WeatherApi
import retrofit2.Retrofit


/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/
class WeatherRepository constructor(retrofit: Retrofit) {

    val weatherApi = retrofit.create(WeatherApi::class.java)

}