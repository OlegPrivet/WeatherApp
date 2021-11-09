package com.olegdev.weatherapp.ui.citydetail

import com.olegdev.weatherapp.repositories.WeatherRepository
import com.olegdev.weatherapp.ui.citydetail.presenter.DetailMVPPresenter
import com.olegdev.weatherapp.ui.citydetail.presenter.DetailPresenter
import com.olegdev.weatherapp.ui.citydetail.view.DetailMVPView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
@Module
class DetailModule {

    @Provides
    fun provideDetailPresenter(detailPresenter: DetailPresenter<DetailMVPView>): DetailMVPPresenter<DetailMVPView> =
        detailPresenter

    @Provides
    fun provideWeatherRepository(retrofit: Retrofit): WeatherRepository {
        return WeatherRepository(retrofit = retrofit)
    }
}