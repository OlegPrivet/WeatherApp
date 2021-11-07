package com.olegdev.weatherapp.ui

import com.olegdev.weatherapp.models.WeatherResponse

/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/

interface DetailView {
    fun loadWeather()
    fun showWeather(weatherModel: WeatherResponse)
    fun loadError(error:String)
}