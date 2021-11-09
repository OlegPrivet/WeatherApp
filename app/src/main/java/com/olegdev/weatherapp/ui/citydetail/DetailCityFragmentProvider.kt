package com.olegdev.weatherapp.ui.citydetail

import com.olegdev.weatherapp.ui.citydetail.view.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
@Module
abstract class DetailCityFragmentProvider {
    @ContributesAndroidInjector(modules = [DetailModule::class])
    internal abstract fun provideDetailCityFragmentFactory(): DetailFragment
}