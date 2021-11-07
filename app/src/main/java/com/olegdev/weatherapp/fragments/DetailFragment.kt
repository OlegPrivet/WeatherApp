package com.olegdev.weatherapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.olegdev.weatherapp.R
import com.olegdev.weatherapp.adapters.DetailAdapter
import com.olegdev.weatherapp.models.Daily
import com.olegdev.weatherapp.models.WeatherResponse
import com.olegdev.weatherapp.presenters.detail.DetailPresenterImpl
import com.olegdev.weatherapp.screens.MainActivity
import com.olegdev.weatherapp.ui.DetailView
import kotlin.math.roundToInt




/**Created by Oleg
 * @Date: 05.11.2021
 * @Email: karandalli35@gmail.com
 **/

class DetailFragment : Fragment(R.layout.fragment_detail), DetailView{

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
        fun getNewInstance(city: String) : DetailFragment {
            val args = Bundle().apply{
                putString("city", city)
            }
            return DetailFragment().apply {
                arguments = args
            }
        }
    }

    private val detailPresenter = DetailPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailPresenter.attachView(this@DetailFragment)
        city = arguments?.getString("city") as String
        setHasOptionsMenu(true);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true);
        (activity as MainActivity).supportActionBar?.setHomeButtonEnabled(true);
        tv_city = view.findViewById(R.id.tv_city)
        tv_temp = view.findViewById(R.id.tv_temp)
        tv_desc = view.findViewById(R.id.tv_description)
        iv_icon = view.findViewById(R.id.iv_icon)
        progressBar = view.findViewById(R.id.progress_circular)
        recyclerView = view.findViewById(R.id.recycler_weather)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        detailAdapter = DetailAdapter()
        recyclerView.adapter = detailAdapter
        detailPresenter.fetchDirect(city)
    }

    override fun onDestroy() {
        super.onDestroy()
        detailPresenter.detachView()
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