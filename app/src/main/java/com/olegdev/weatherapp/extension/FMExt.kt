package com.olegdev.weatherapp.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.olegdev.weatherapp.R


/**Created by Oleg
 * @Date: 10.11.2021
 * @Email: karandalli35@gmail.com
 **/
internal fun FragmentManager.removeFragment(tag: String,
                                            slideIn: Int = R.anim.slide_left,
                                            slideOut: Int = R.anim.slide_right) {
    this.findFragmentByTag(tag)?.let {
        this.beginTransaction()
        .disallowAddToBackStack()
        .setCustomAnimations(slideIn, slideOut)
        .remove(it)
        .commitNow()
    }
}

internal fun FragmentManager.addFragment(containerViewId: Int,
                                         fragment: Fragment,
                                         tag: String,
                                         slideIn: Int = R.anim.slide_left,
                                         slideOut: Int = R.anim.slide_right) {
    this.beginTransaction().disallowAddToBackStack()
        .setCustomAnimations(slideIn, slideOut)
        .add(containerViewId, fragment, tag)
        .commit()
}