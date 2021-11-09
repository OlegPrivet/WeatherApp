package com.olegdev.weatherapp.ui.main.presenter

import com.olegdev.weatherapp.ui.base.presenter.MVPPresenter
import com.olegdev.weatherapp.ui.base.view.BaseFragment
import com.olegdev.weatherapp.ui.main.view.MainMVPView


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
interface MainMVPPresenter<V : MainMVPView> : MVPPresenter<V> {
    fun onShowFragment(baseFragment: BaseFragment, tag: String)
}