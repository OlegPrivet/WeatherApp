package com.olegdev.weatherapp.presenters.list

import com.olegdev.weatherapp.models.CityModel
import com.olegdev.weatherapp.repositories.CitiesRepository
import com.olegdev.weatherapp.ui.ListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/

class ListPresenterImpl : ListPresenter {

    private val TAG = ListPresenterImpl::class.simpleName

    private val citiesRepository = CitiesRepository.get()
    private var viewState: WeakReference<ListView>? = null
    private val compositeDisposable = CompositeDisposable()

    fun attachView(listView: ListView) {
        viewState = WeakReference(listView)
    }

    fun detachView() {
        viewState = null
        compositeDisposable.dispose()
    }

    override fun getListCities() {
        viewState?.get()?.loadCities()
        compositeDisposable.add(
            citiesRepository.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState?.get()?.showCities(it)
                }, {
                    viewState?.get()?.loadError("Error: ${it.localizedMessage}")
                })
        )
    }

    override fun saveCity(cityModel: CityModel) {
        viewState?.get()?.loadCities()
        compositeDisposable.add(
            citiesRepository.saveCity(cityModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getListCities()
                }, {
                    viewState?.get()?.loadError("Error: ${it.localizedMessage}")
                })
        )
    }

    override fun getCity(city: String): Boolean {
        var cityFind = false
        viewState?.get()?.loadCities()
        compositeDisposable.add(
            citiesRepository.getCity(city = city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    cityFind =  true
                }, {
                    viewState?.get()?.loadError("Error: ${it.localizedMessage}")
                })
        )
        return cityFind
    }

    override fun removeCity(city: CityModel) {
        viewState?.get()?.loadCities()
        compositeDisposable.add(
            citiesRepository.deleteCity(city = city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getListCities()
                }, {
                    viewState?.get()?.loadError("Error: ${it.localizedMessage}")
                })
        )
    }
}