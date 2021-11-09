package com.olegdev.weatherapp.di.component

import android.app.Application
import com.olegdev.weatherapp.app.WeatherApp
import com.olegdev.weatherapp.di.builder.ActivityBuilder
import com.olegdev.weatherapp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBuilder::class, AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: WeatherApp)
}
