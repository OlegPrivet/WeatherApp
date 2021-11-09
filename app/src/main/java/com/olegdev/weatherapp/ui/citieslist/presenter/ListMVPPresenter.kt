package com.olegdev.weatherapp.ui.citieslist.presenter

import com.olegdev.weatherapp.models.CityModel
import com.olegdev.weatherapp.ui.base.presenter.MVPPresenter
import com.olegdev.weatherapp.ui.citieslist.view.ListMVPView


/**Created by Oleg
 * @Date: 10.11.2021
 * @Email: karandalli35@gmail.com
 **/
interface ListMVPPresenter<V : ListMVPView> : MVPPresenter<V> {
    fun getListCities()
    fun saveCity(cityModel: CityModel)
    fun getCity(city: String) : Boolean
    fun removeCity(city: CityModel)
}