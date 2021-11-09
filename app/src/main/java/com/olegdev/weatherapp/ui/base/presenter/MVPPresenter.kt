package com.olegdev.weatherapp.ui.base.presenter

import com.olegdev.weatherapp.ui.base.view.MVPView


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
interface MVPPresenter<V : MVPView> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}