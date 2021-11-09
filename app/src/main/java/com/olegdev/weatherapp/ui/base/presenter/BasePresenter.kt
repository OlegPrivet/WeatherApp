package com.olegdev.weatherapp.ui.base.presenter

import com.olegdev.weatherapp.ui.base.view.MVPView


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/

abstract class BasePresenter<V : MVPView>  : MVPPresenter<V>  {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun getView(): V? = view

    override fun onDetach() {
        view = null
    }
}