package com.olegdev.weatherapp.di.builder

import com.olegdev.weatherapp.ui.citieslist.CitiesListFragmentProvider
import com.olegdev.weatherapp.ui.citydetail.DetailCityFragmentProvider
import com.olegdev.weatherapp.ui.main.MainActivityModule
import com.olegdev.weatherapp.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (CitiesListFragmentProvider::class), (DetailCityFragmentProvider::class)])
    abstract fun bindMainActivity(): MainActivity
}