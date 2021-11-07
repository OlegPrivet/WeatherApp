package com.olegdev.weatherapp.ui

import com.olegdev.weatherapp.models.CityModel


/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/
interface ListView {
    fun loadCities()
    fun showCities(cities: List<CityModel>)
    fun loadError(error:String)
}