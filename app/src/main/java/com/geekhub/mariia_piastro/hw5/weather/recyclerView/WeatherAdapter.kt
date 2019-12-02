package com.geekhub.mariia_piastro.hw5.weather.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekhub.mariia_piastro.hw5.weather.R
import com.geekhub.mariia_piastro.hw5.weather.entities.Weather
import com.geekhub.mariia_piastro.hw5.weather.entities.WeatherResponse

class WeatherAdapter(private var weatherResponses: ArrayList<WeatherResponse>) :
    RecyclerView.Adapter<WeatherViewHolder>() {
//
//    interface Callback {
//        fun onItemClick(weather: Weather)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherResponses.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherResponses[position])
    }

}