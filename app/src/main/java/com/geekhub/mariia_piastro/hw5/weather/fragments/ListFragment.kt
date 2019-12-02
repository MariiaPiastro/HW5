package com.geekhub.mariia_piastro.hw5.weather.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekhub.mariia_piastro.hw5.weather.R
import com.geekhub.mariia_piastro.hw5.weather.entities.WeatherResponse
import com.geekhub.mariia_piastro.hw5.weather.network.Apifactory
import com.geekhub.mariia_piastro.hw5.weather.recyclerView.WeatherAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    var weatherResponses: List<WeatherResponse> = listOf()

    private val location = "Cherkasy"
    private val units = "metric"

    companion object {
        fun newInstance(): ListFragment =
            ListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        Apifactory.getCurrentWeather(location, units)
            .enqueue(object : Callback<List<WeatherResponse>> {

                override fun onFailure(call: Call<List<WeatherResponse>>, t: Throwable) {
                    Log.d("err", "ERR")
                }

                override fun onResponse(
                    call: Call<List<WeatherResponse>>,
                    response: Response<List<WeatherResponse>>
                ) {
                    weatherResponses.addAll(response.body())
                    recyclerView.adapter = WeatherAdapter(weatherResponses)
                }
            })
    }

}