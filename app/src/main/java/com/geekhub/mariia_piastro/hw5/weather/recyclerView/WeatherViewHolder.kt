package com.geekhub.mariia_piastro.hw5.weather.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.geekhub.mariia_piastro.hw5.weather.entities.Weather
import com.geekhub.mariia_piastro.hw5.weather.entities.WeatherResponse
import com.geekhub.mariia_piastro.hw5.weather.recyclerView.WeatherAdapter.*
import kotlinx.android.synthetic.main.list_item.view.*


class WeatherViewHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View) {
    }

    fun bind(weatherResponse: WeatherResponse) {
    }
}