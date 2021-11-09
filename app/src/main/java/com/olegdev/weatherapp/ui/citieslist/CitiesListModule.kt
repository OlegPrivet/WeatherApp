package com.olegdev.weatherapp.ui.citieslist

import com.olegdev.weatherapp.db.CityDataBase
import com.olegdev.weatherapp.repositories.CitiesRepository
import com.olegdev.weatherapp.ui.citieslist.presenter.ListMVPPresenter
import com.olegdev.weatherapp.ui.citieslist.presenter.ListPresenter
import com.olegdev.weatherapp.ui.citieslist.view.ListMVPView
import dagger.Module
import dagger.Provides


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
@Module
class CitiesListModule {

    @Provides
    fun provideCitiesListPresenter(listPresenter: ListPresenter<ListMVPView>): ListMVPPresenter<ListMVPView> =
        listPresenter

    @Provides
    fun provideCitiesRepository(database: CityDataBase): CitiesRepository {
        return CitiesRepository(database = database)
    }
}