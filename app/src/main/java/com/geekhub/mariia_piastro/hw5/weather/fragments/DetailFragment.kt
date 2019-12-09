package com.geekhub.mariia_piastro.hw5.weather.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekhub.mariia_piastro.hw5.weather.R
import com.geekhub.mariia_piastro.hw5.weather.entities.WeatherResponse
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlin.math.roundToInt

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(weatherResponse: WeatherResponse?): DetailFragment {
            val args = Bundle()
            args.putSerializable("weather", weatherResponse)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weatherDetail = arguments!!.getSerializable("weather") as WeatherResponse
        with(view) {
            textViewDetailTemperature.text = String.format("%d\u00B0", weatherDetail.main.temp.roundToInt())
            textViewDetailDescription.text = weatherDetail.weather[0].description
            textViewDetailHumidity.text = String.format("Влажность: %.2f", weatherDetail.main.humidity)
            textViewDetailPressure.text = String.format("Атмосферное давление: %.2f",weatherDetail.main.pressure)
            textViewDetailWindSpeed.text = String.format("Скорость ветра: %.2f", weatherDetail.wind.speed)
            textViewDetailDirectionWind.text = String.format("Направление ветра: %.2f", weatherDetail.wind.deg)
        }
    }
}
