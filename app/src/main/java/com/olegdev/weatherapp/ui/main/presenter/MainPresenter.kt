package com.olegdev.weatherapp.ui.main.presenter

import com.olegdev.weatherapp.ui.base.presenter.BasePresenter
import com.olegdev.weatherapp.ui.base.view.BaseFragment
import com.olegdev.weatherapp.ui.citieslist.view.ListCitiesFragment
import com.olegdev.weatherapp.ui.main.view.MainMVPView
import javax.inject.Inject


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
class MainPresenter<V : MainMVPView> @Inject internal constructor() : BasePresenter<V>(), MainMVPPresenter<V>  {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        getView()?.showFragment(ListCitiesFragment.getNewInstance(),
            ListCitiesFragment.TAG.toString()
        )
    }

    override fun onShowFragment(baseFragment: BaseFragment, tag: String) {
        getView()?.showFragment(baseFragment = baseFragment, tag)
    }
}