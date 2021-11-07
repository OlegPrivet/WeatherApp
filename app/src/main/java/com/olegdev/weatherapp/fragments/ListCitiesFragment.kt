package com.olegdev.weatherapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.olegdev.weatherapp.R
import com.olegdev.weatherapp.adapters.ListCitiesAdapter
import com.olegdev.weatherapp.adapters.baseadapter.BaseAdapterCallback
import com.olegdev.weatherapp.models.CityModel
import com.olegdev.weatherapp.presenters.list.ListPresenterImpl
import com.olegdev.weatherapp.ui.ListView

/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/
class ListCitiesFragment : Fragment(R.layout.fragment_list_cities), ListView {

    private val TAG = ListCitiesFragment::class.simpleName

    companion object {
        fun getNewInstance(): ListCitiesFragment {
            return ListCitiesFragment()
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var citiesAdapter: ListCitiesAdapter
    private var listPresenter = ListPresenterImpl()

    private lateinit var progressBar: ProgressBar
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listPresenter.attachView(this@ListCitiesFragment)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progress)
        fab = view.findViewById(R.id.add_city)
        recyclerView = view.findViewById(R.id.recycler_list_cities)
        citiesAdapter = ListCitiesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = citiesAdapter
        citiesAdapter.attachCallback(object : BaseAdapterCallback<CityModel> {
            override fun onItemClick(model: CityModel, view: View) {
                requireActivity().supportFragmentManager.commit {
                    replace(R.id.fragment_container, DetailFragment.getNewInstance(model.city))
                    addToBackStack("detail")
                }
            }

            override fun onLongClick(model: CityModel, view: View): Boolean {
                return false
            }
        })
        fab.setOnClickListener {
            MaterialDialog(requireActivity())
                .title(res = R.string.input_city)
                .cornerRadius(literalDp = 8.0f)
                .cancelOnTouchOutside(cancelable = true)
                .show {
                    input(
                        hintRes = R.string.hint_city,
                        inputType = InputType.TYPE_CLASS_TEXT
                    ) { dialog, text ->
                        listPresenter.saveCity(cityModel = CityModel(city = text.toString()))
                        dialog.dismiss()
                    }
                    positiveButton(R.string.add_button)
                }
        }
        listPresenter.getListCities()
    }

    override fun loadCities() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showCities(cities: List<CityModel>) {
        citiesAdapter.setList(cities)
        progressBar.visibility = View.GONE
    }

    override fun loadError(error: String) {
        progressBar.visibility = View.GONE
        Log.e(TAG, error)
    }

    override fun onDestroy() {
        super.onDestroy()
        listPresenter.detachView()
    }

}