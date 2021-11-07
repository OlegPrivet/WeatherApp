package com.olegdev.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.olegdev.weatherapp.R
import com.olegdev.weatherapp.adapters.baseadapter.BaseAdapter
import com.olegdev.weatherapp.adapters.baseadapter.BaseViewHolder
import com.olegdev.weatherapp.models.Daily
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


/**Created by Oleg
 * @Date: 06.11.2021
 * @Email: karandalli35@gmail.com
 **/

class DetailAdapter : BaseAdapter<Daily>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Daily> {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        )
    }

    class ViewHolder(itemView: View) :
        BaseViewHolder<Daily>(itemView) {

        val tv_date: TextView = itemView.findViewById(R.id.tv_date)
        val tv_description: TextView = itemView.findViewById(R.id.tv_description)
        val tv_n_temp: TextView = itemView.findViewById(R.id.tv_n_temp)
        val tv_d_temp: TextView = itemView.findViewById(R.id.tv_d_temp)
        val img_weather_type: ImageView = itemView.findViewById(R.id.img_weather_type)

        override fun bind(model: Daily) {
            tv_date.text =
                SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(Date(model.dt * 1000L))
                    .toString()
            tv_description.text = model.weather[0].description
            tv_d_temp.text = model.temp.day.roundToInt().toString()
            tv_n_temp.text = model.temp.night.roundToInt().toString()
            val image_url = "http://openweathermap.org/img/wn/${model.weather[0].icon}@2x.png"
            Glide.with(img_weather_type.context)
                .load(image_url)
                .centerCrop()
                .into(img_weather_type);


        }

    }

}