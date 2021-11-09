package com.olegdev.weatherapp.ui.citieslist.presenter

import com.olegdev.weatherapp.models.CityModel
import com.olegdev.weatherapp.repositories.CitiesRepository
import com.olegdev.weatherapp.ui.base.presenter.BasePresenter
import com.olegdev.weatherapp.ui.citieslist.view.ListMVPView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/

class ListPresenter <V : ListMVPView> @Inject constructor(private val citiesRepository: CitiesRepository) :
    BasePresenter<V>(), ListMVPPresenter<V> {

    private val TAG = ListPresenter::class.simpleName

    private val compositeDisposable = CompositeDisposable()

    override fun onDetach() {
        compositeDisposable.dispose()
        super.onDetach()
    }

    override fun getListCities() {
        getView()?.loadCities()
        compositeDisposable.add(
            citiesRepository.getCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getView()?.showCities(it)
                }, {
                    getView()?.loadError("Error: ${it.localizedMessage}")
                })
        )
    }

    override fun saveCity(cityModel: CityModel) {
        getView()?.loadCities()
        compositeDisposable.add(
            citiesRepository.saveCity(cityModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getListCities()
                }, {
                    getView()?.loadError("Error: ${it.localizedMessage}")
                })
        )
    }

    override fun getCity(city: String): Boolean {
        var cityFind = false
        getView()?.loadCities()
        compositeDisposable.add(
            citiesRepository.getCity(city = city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    cityFind =  true
                }, {
                    getView()?.loadError("Error: ${it.localizedMessage}")
                })
        )
        return cityFind
    }

    override fun removeCity(city: CityModel) {
        getView()?.loadCities()
        compositeDisposable.add(
            citiesRepository.deleteCity(city = city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getListCities()
                }, {
                    getView()?.loadError("Error: ${it.localizedMessage}")
                })
        )
    }
}