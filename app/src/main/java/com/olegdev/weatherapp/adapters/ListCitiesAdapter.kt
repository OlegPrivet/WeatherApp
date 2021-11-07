package com.olegdev.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.olegdev.weatherapp.R
import com.olegdev.weatherapp.adapters.baseadapter.BaseAdapter
import com.olegdev.weatherapp.adapters.baseadapter.BaseViewHolder
import com.olegdev.weatherapp.models.CityModel

class ListCitiesAdapter : BaseAdapter<CityModel>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CityModel> {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.llist_cities_item, parent, false)
        )
    }

    class ViewHolder(itemView: View) : BaseViewHolder<CityModel>(itemView) {

        val tv_city: TextView = itemView.findViewById(R.id.tv_name)

        override fun bind(model: CityModel) {
            tv_city.text = model.city
        }

    }

}
