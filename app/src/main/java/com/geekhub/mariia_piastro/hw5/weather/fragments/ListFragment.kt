package com.geekhub.mariia_piastro.hw5.weather.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekhub.mariia_piastro.hw5.weather.MainApplication
import com.geekhub.mariia_piastro.hw5.weather.R
import com.geekhub.mariia_piastro.hw5.weather.entities.ListResponse
import com.geekhub.mariia_piastro.hw5.weather.entities.WeatherResponse
import com.geekhub.mariia_piastro.hw5.weather.network.Apifactory
import com.geekhub.mariia_piastro.hw5.weather.recyclerView.WeatherAdapter
import kotlinx.android.synthetic.main.fragment_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    val pref = PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext())
    private val location = pref.getString("location", "Cherkasy")
    private val units = pref.getString("units", "metric")

    var weatherResponses: ArrayList<WeatherResponse> = ArrayList()

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
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        Apifactory.getCurrentWeather(location, units)
            .enqueue(object : Callback<ListResponse> {

                override fun onResponse(
                    call: Call<ListResponse>,
                    response: Response<ListResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseList = response.body()!!

                        for (responseContent in responseList.responses) {
                            weatherResponses.add(responseContent)
                            Log.d("response", responseContent.toString())
                        }
                        view.recyclerView.adapter = WeatherAdapter(weatherResponses)
                    }
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    Log.d("err", "ERR")
                    t.printStackTrace()
                }
            })
    }

}