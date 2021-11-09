package com.olegdev.weatherapp.ui.citydetail.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olegdev.weatherapp.R
import com.olegdev.weatherapp.adapters.DetailAdapter
import com.olegdev.weatherapp.models.Daily
import com.olegdev.weatherapp.models.WeatherResponse
import com.olegdev.weatherapp.ui.base.view.BaseFragment
import com.olegdev.weatherapp.ui.citydetail.presenter.DetailPresenter
import com.olegdev.weatherapp.ui.main.view.MainActivity
import javax.inject.Inject
import kotlin.math.roundToInt


/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/

class DetailFragment : BaseFragment(), DetailMVPView {

    private val TAG = DetailFragment::class.simpleName

    private lateinit var recyclerView: RecyclerView
    private lateinit var detailAdapter: DetailAdapter
    private lateinit var progressBar: ProgressBar

    private lateinit var tv_city: TextView
    private lateinit var tv_temp: TextView
    private lateinit var tv_desc: TextView
    private lateinit var iv_icon: ImageView

    private var city = "Kaluga"

    companion object{
        val TAG = DetailFragment::class.simpleName
        fun getNewInstance(city: String) : DetailFragment {
            val args = Bundle().apply{
                putString("city", city)
            }
            return DetailFragment().apply {
                arguments = args
            }
        }
    }

    @Inject internal lateinit var presenter: DetailPresenter<DetailMVPView>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        city = arguments?.getString("city") as String
        setHasOptionsMenu(true);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this@DetailFragment)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true);
        (activity as MainActivity).supportActionBar?.setHomeButtonEnabled(true);
        tv_city = view?.findViewById(R.id.tv_city) as TextView
        tv_temp = view?.findViewById(R.id.tv_temp) as TextView
        tv_desc = view?.findViewById(R.id.tv_description) as TextView
        iv_icon = view?.findViewById(R.id.iv_icon) as ImageView
        progressBar = view?.findViewById(R.id.progress_circular) as ProgressBar
        recyclerView = view?.findViewById(R.id.recycler_weather) as RecyclerView
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        detailAdapter = DetailAdapter()
        recyclerView.adapter = detailAdapter
        presenter.fetchDirect(city)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun loadWeather() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showWeather(weatherModel: WeatherResponse) {
        tv_city.text = city
        tv_desc.text = weatherModel.current.weather[0].description
        tv_temp.text = weatherModel.current.temp.roundToInt().toString()
        val image_url = "http://openweathermap.org/img/wn/${weatherModel.current.weather[0].icon}@2x.png"
        Glide.with(requireContext())
            .load(image_url)
            .centerCrop()
            .into(iv_icon);
        progressBar.visibility = View.GONE
        setList(weatherModel.daily)
    }

    override fun loadError(error: String) {
        Log.e(TAG, error)
        progressBar.visibility = View.GONE
    }

    private fun setList(daily: List<Daily>){
        detailAdapter.setList(dataList = daily)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                (activity as MainActivity).onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}