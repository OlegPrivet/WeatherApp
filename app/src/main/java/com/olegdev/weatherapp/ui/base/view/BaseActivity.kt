package com.olegdev.weatherapp.ui.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
abstract class BaseActivity: AppCompatActivity(), MVPView, BaseFragment.CallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }

    private fun performDI() = AndroidInjection.inject(this)
}