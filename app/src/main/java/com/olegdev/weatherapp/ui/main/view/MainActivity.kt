package com.olegdev.weatherapp.ui.main.view

import android.os.Bundle
import androidx.fragment.app.commit
import com.olegdev.weatherapp.R
import com.olegdev.weatherapp.ui.base.view.BaseActivity
import com.olegdev.weatherapp.ui.base.view.BaseFragment
import com.olegdev.weatherapp.ui.main.presenter.MainMVPPresenter
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), MainMVPView, HasAndroidInjector {

    @Inject
    internal lateinit var presenter: MainMVPPresenter<MainMVPView>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.onAttach(this)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun showFragment(baseFragment: BaseFragment, tag: String) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container, baseFragment)
            addToBackStack(null)
        }
    }

    override fun androidInjector() = fragmentDispatchingAndroidInjector

}