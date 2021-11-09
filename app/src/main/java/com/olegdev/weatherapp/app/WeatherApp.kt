package com.olegdev.weatherapp.app

import android.app.Application
import com.olegdev.weatherapp.di.component.AppComponent
import com.olegdev.weatherapp.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/
class WeatherApp : Application(), HasAndroidInjector {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }


    fun getAppComponent(): AppComponent {
        return component
    }

    companion object {
        lateinit var instance: WeatherApp private set
    }

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector() = activityDispatchingAndroidInjector


}