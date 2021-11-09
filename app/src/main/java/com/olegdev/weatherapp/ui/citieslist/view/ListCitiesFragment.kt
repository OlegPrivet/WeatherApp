package com.olegdev.weatherapp.ui.citieslist.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.olegdev.weatherapp.R
import com.olegdev.weatherapp.adapters.ListCitiesAdapter
import com.olegdev.weatherapp.adapters.baseadapter.BaseAdapterCallback
import com.olegdev.weatherapp.models.CityModel
import com.olegdev.weatherapp.ui.base.view.BaseFragment
import com.olegdev.weatherapp.ui.citieslist.presenter.ListMVPPresenter
import com.olegdev.weatherapp.ui.citydetail.view.DetailFragment
import javax.inject.Inject

/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/
class ListCitiesFragment : BaseFragment(), ListMVPView {

    val TAG = ListCitiesFragment::class.simpleName

    companion object {

        val TAG = ListCitiesFragment::class.simpleName
        fun getNewInstance(): ListCitiesFragment {
            return ListCitiesFragment()
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var citiesAdapter: ListCitiesAdapter

    @Inject
    internal lateinit var presenter: ListMVPPresenter<ListMVPView>

    private lateinit var progressBar: ProgressBar
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_list_cities, container, false)

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this@ListCitiesFragment)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        progressBar = view?.findViewById(R.id.progress) as ProgressBar
        fab = view?.findViewById(R.id.add_city) as FloatingActionButton
        recyclerView = view?.findViewById(R.id.recycler_list_cities) as RecyclerView
        citiesAdapter = ListCitiesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = citiesAdapter
        citiesAdapter.attachCallback(object : BaseAdapterCallback<CityModel> {
            override fun onItemClick(model: CityModel, view: View) {
                requireActivity().supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    replace(R.id.fragment_container, DetailFragment.getNewInstance(model.city))
                    addToBackStack(null)
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
                        presenter.saveCity(cityModel = CityModel(city = text.toString()))
                        dialog.dismiss()
                    }
                    positiveButton(R.string.add_button)
                }
        }
        presenter.getListCities()
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

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

}