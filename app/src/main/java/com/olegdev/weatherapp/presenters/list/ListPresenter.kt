package com.olegdev.weatherapp.presenters.list

import com.olegdev.weatherapp.models.CityModel


/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/
interface ListPresenter {
    fun getListCities()
    fun saveCity(cityModel: CityModel)
    fun getCity(city: String) : Boolean
    fun removeCity(city: CityModel)
}