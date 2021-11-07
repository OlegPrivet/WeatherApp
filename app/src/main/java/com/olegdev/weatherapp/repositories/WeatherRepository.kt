package com.olegdev.weatherapp.repositories

import android.content.Context
import com.olegdev.weatherapp.constants.UrlConstants
import com.olegdev.weatherapp.data.remote.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/
class WeatherRepository constructor(context: Context) {

    private val httpLoginInterceptor: HttpLoggingInterceptor
        get() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor)
            .build()

    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(UrlConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    val weatherApi: WeatherApi
        get() = retrofit.create(WeatherApi::class.java)

    companion object {
        private var INSTANCE: WeatherRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = WeatherRepository(context = context)
            }
        }

        fun get(): WeatherRepository {
            return INSTANCE ?: throw IllegalStateException("FileRepository must be initialized")
        }
    }
}