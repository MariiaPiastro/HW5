package com.geekhub.mariia_piastro.hw5.weather.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val weatherAdapter by lazy { WeatherAdapter(emptyList()) }
    private lateinit var mItemClick: WeatherAdapter.ItemClick

    private val pref
        get() = PreferenceManager.getDefaultSharedPreferences(requireContext())
    private lateinit var location: String
    private lateinit var units: String

    companion object {
        fun newInstance(): ListFragment =
            ListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        location = pref.getString("location", "London")!!
        units = pref.getString("units", "metric")!!
        mItemClick = context as WeatherAdapter.ItemClick
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view.recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = weatherAdapter.apply {
                itemClick = mItemClick
            }
        }
        getWeather()
    }

    override fun onResume() {
        super.onResume()
        location = pref.getString("location", "London")!!
        units = pref.getString("units", "metric")!!
    }

    private fun getWeather() {
        Apifactory.getCurrentWeather(location, units)
            .enqueue(object : Callback<ListResponse> {

                override fun onResponse(
                    call: Call<ListResponse>,
                    response: Response<ListResponse>
                ) {
                    if (response.isSuccessful) {
                        weatherAdapter.setData(response.body()?.list ?: emptyList())
                        weatherAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    Log.d("err", "ERR")
                    t.printStackTrace()
                }
            })
    }
}