package com.olegdev.weatherapp.models

import com.google.gson.annotations.SerializedName


/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/

data class WeatherResponse(
    @SerializedName("lat")
    val lat: Float,
    @SerializedName("lon")
    val lon: Float,
    @SerializedName("current")
    val current: Current,
    @SerializedName("daily")
    val daily: List<Daily>,
)

data class Current(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("feels_like")
    val feels_like: Float,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("visibility")
    val visibility: Long,
    @SerializedName("wind_speed")
    val wind_speed: Float,
    @SerializedName("wind_deg")
    val wind_deg: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
)

data class Daily(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long,
    @SerializedName("moonrise")
    val moonrise: Long,
    @SerializedName("moonset")
    val moonset: Long,
    @SerializedName("temp")
    val temp: Temp,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("wind_speed")
    val wind_speed: Float,
    @SerializedName("wind_deg")
    val wind_deg: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
)



data class Temp(
    @SerializedName("day")
    val day: Float,
    @SerializedName("night")
    val night: Float
)

data class Weather(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)
