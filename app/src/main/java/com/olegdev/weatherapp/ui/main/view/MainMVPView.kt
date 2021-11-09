package com.olegdev.weatherapp.ui.main.view

import com.olegdev.weatherapp.ui.base.view.BaseFragment
import com.olegdev.weatherapp.ui.base.view.MVPView


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
interface MainMVPView : MVPView {
    fun showFragment(baseFragment: BaseFragment, tag: String)
}