package com.olegdev.weatherapp.presenters.detail

import android.util.Log
import com.olegdev.weatherapp.repositories.WeatherRepository
import com.olegdev.weatherapp.ui.DetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/
class DetailPresenterImpl : DetailPresenter {

    private val TAG = DetailPresenterImpl::class.simpleName

    private val weatherRepository = WeatherRepository.get()
    private var viewState: WeakReference<DetailView>? = null
    private val compositeDisposable = CompositeDisposable()

    fun attachView(detailView: DetailView) {
        viewState = WeakReference(detailView)
    }

    fun detachView() {
        viewState = null
        compositeDisposable.dispose()
    }

    override fun fetchDirect(
        city: String,
    ) {
        viewState?.get()?.loadWeather()
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
                    viewState?.get()?.showWeather(it)
                },{
                    viewState?.get()?.loadError("Error: ${it.localizedMessage}")
                })

        )
    }


}