package com.olegdev.weatherapp.ui.citydetail.view

import com.olegdev.weatherapp.models.WeatherResponse
import com.olegdev.weatherapp.ui.base.view.MVPView


/**Created by Oleg
 * @Date: 10.11.2021
 * @Email: karandalli35@gmail.com
 **/
interface DetailMVPView : MVPView {
    fun loadWeather()
    fun showWeather(weatherModel: WeatherResponse)
    fun loadError(error:String)
}