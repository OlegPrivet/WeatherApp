package com.olegdev.weatherapp.ui.citieslist

import com.olegdev.weatherapp.ui.citieslist.view.ListCitiesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
@Module
abstract class CitiesListFragmentProvider {
    @ContributesAndroidInjector(modules = [CitiesListModule::class])
    internal abstract fun provideCitiesListFragmentFactory(): ListCitiesFragment
}