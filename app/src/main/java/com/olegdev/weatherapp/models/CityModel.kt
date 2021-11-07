package com.olegdev.weatherapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*


/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/
@Entity
@Parcelize
data class CityModel(
    @PrimaryKey val uuid: String = UUID.randomUUID().toString(),
    val city: String
) : Parcelable
