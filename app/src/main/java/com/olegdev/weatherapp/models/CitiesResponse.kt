package com.olegdev.weatherapp.models

import com.google.gson.annotations.SerializedName
import java.util.*

/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/
data class CitiesResponse (
    @SerializedName("name")
    val name: String,
    @SerializedName("lon")
    val lon: Float,
    @SerializedName("lat")
    val lat: Float,
    @SerializedName("local_names")
    val local_names: Dictionary<String, String>,
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state: String,
)