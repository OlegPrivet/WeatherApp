package com.olegdev.weatherapp.ui.citydetail.presenter

import android.util.Log
import com.olegdev.weatherapp.repositories.WeatherRepository
import com.olegdev.weatherapp.ui.base.presenter.BasePresenter
import com.olegdev.weatherapp.ui.citydetail.view.DetailMVPView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/

class DetailPresenter<V : DetailMVPView>  @Inject constructor(private val weatherRepository: WeatherRepository):
    BasePresenter<V>(), DetailMVPPresenter<V> {

    private val TAG = DetailPresenter::class.simpleName

    private val compositeDisposable = CompositeDisposable()

    override fun onDetach() {
        compositeDisposable.dispose()
        super.onDetach()
    }

    override fun fetchDirect(
        city: String,
    ) {
        getView()?.loadWeather()
        compositeDisposable.add(
            weatherRepository.weatherApi.getDirect(q = city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ list ->
                    fetchWeather(list.last().lat.toString(), list.last().lon.toString())
                    Log.e(TAG, "$list")
                }, {
                    Log.e(TAG, "Error: ${it.localizedMessage}")
                })
        )

    }

    private fun fetchWeather(lat: String, lon: String) {
        compositeDisposable.add(
            weatherRepository.weatherApi.getWeather(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    getView()?.showWeather(it)
                },{
                    getView()?.loadError("Error: ${it.localizedMessage}")
                })
        )
    }
}