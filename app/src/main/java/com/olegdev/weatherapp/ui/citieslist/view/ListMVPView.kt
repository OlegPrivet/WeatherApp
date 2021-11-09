package com.olegdev.weatherapp.ui.citieslist.view

import com.olegdev.weatherapp.models.CityModel
import com.olegdev.weatherapp.ui.base.view.MVPView


/**Created by Oleg
 * @Date: 10.11.2021
 * @Email: karandalli35@gmail.com
 **/
interface ListMVPView : MVPView {
    fun loadCities()
    fun showCities(cities: List<CityModel>)
    fun loadError(error:String)
}