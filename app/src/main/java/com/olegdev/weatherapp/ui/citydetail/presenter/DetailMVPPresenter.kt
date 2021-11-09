package com.olegdev.weatherapp.ui.citydetail.presenter

import com.olegdev.weatherapp.ui.base.presenter.MVPPresenter
import com.olegdev.weatherapp.ui.citydetail.view.DetailMVPView


/**Created by Oleg
 * @Date: 10.11.2021
 * @Email: karandalli35@gmail.com
 **/
interface DetailMVPPresenter<V : DetailMVPView> : MVPPresenter<V> {
    fun fetchDirect(city: String)
}