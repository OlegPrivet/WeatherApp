package com.olegdev.weatherapp.ui.main

import com.olegdev.weatherapp.ui.main.presenter.MainMVPPresenter
import com.olegdev.weatherapp.ui.main.presenter.MainPresenter
import com.olegdev.weatherapp.ui.main.view.MainMVPView
import dagger.Module
import dagger.Provides


/**Created by Oleg
 * @Date: 09.11.2021
 * @Email: karandalli35@gmail.com
 **/
@Module
class MainActivityModule {
    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainMVPView>)
            : MainMVPPresenter<MainMVPView> = mainPresenter
}