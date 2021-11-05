package com.olegdev.weatherapp.models

import com.google.gson.annotations.SerializedName


/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/

data class WeatherResponse(
    val coord: CoordItem,
    val weathers: List<WeatherItem>,
    val base: String,
    val main: MainItem,
    val visibility: Long,
    val wind: WindItem,
    val clouds: CloudsItem,
    val dt: Long,
    val sys: SysItem,
    val timezone: Long,
    val id: Int,
    val name: String,
    val cod: Int
)

data class SysItem (
    @SerializedName("type")
    val type: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: Float,
    @SerializedName("country")
    val country: String,
    @SerializedName("sunrise")
    val sunrise: Long,
    @SerializedName("sunset")
    val sunset: Long
)

data class CloudsItem (
    @SerializedName("all")
    val all: Int,
)

data class WindItem(
    @SerializedName("speed")
    val speed: Float,
    @SerializedName("deg")
    val deg: Float,
)

data class MainItem(
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("feels_like")
    val feels_like: Float,
    @SerializedName("temp_min")
    val temp_min: Float,
    @SerializedName("temp_max")
    val temp_max: Float,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int
)

data class CoordItem(
    @SerializedName("lon")
    val lon: Float,
    @SerializedName("lat")
    val lat: Float
)

data class WeatherItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)
