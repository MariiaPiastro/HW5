package com.geekhub.mariia_piastro.hw5.weather.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekhub.mariia_piastro.hw5.weather.R
import com.geekhub.mariia_piastro.hw5.weather.entities.WeatherResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class WeatherAdapter(private var weatherResponses: ArrayList<WeatherResponse>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    interface Callback {
        fun onItemClick(weatherResponse: WeatherResponse)
    }

    var callback: Callback? = null

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

   inner class WeatherViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

       init {
           view.setOnClickListener {
               callback?.onItemClick(weatherResponses[adapterPosition])
           }
       }

       fun bind(weatherResponse: WeatherResponse) {
           view.textViewDate.text = weatherResponse.date
           view.textViewDescription.text = weatherResponse.weather[0].description
           view.textViewTemperature.text = weatherResponse.main.temp.toString()
           val icon: String = weatherResponse.weather[0].icon
           val url = "http://openweathermap.org/img/wn/$icon@2x.png"
           Picasso.get().load(url).into(view.imageViewWeatherIcon)
       }
   }
}