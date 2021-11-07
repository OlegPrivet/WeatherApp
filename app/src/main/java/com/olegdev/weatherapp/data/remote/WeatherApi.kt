package com.olegdev.weatherapp.data.remote

import com.olegdev.weatherapp.constants.UrlConstants
import com.olegdev.weatherapp.models.DirectResponse
import com.olegdev.weatherapp.models.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/
interface WeatherApi {

    @GET("./data/2.5/onecall?")
    fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String = "hourly,minutely,alerts",
        @Query("appid") appid: String = UrlConstants.APPID,
        @Query("units") units: String = "metric"
    ): Single<WeatherResponse>

    @GET("./geo/1.0/direct?")
    fun getDirect(
        @Query("q") q: String,
        @Query("appid") appid: String = UrlConstants.APPID,
        @Query("limit") limit: Int = 5,
        @Query("lang") lang: String = "ru"
    ): Single<List<DirectResponse>>

}