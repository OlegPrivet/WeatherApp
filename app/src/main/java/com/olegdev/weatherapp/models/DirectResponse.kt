package com.olegdev.weatherapp.models

import com.google.gson.annotations.SerializedName

/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/
data class DirectResponse (
    @SerializedName("name")
    val name: String,
    @SerializedName("lon")
    val lon: Float,
    @SerializedName("lat")
    val lat: Float,
    @SerializedName("country")
    val country: String
)