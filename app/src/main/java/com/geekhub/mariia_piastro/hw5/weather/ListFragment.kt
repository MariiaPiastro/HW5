package com.geekhub.mariia_piastro.hw5.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekhub.mariia_piastro.hw5.weather.entities.Weather
import com.geekhub.mariia_piastro.hw5.weather.recyclerView.WeatherAdapter
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment: Fragment() {

    private var weather: ArrayList<Weather> = ArrayList()

    companion object {
        fun newInstance(): ListFragment = ListFragment()
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
            adapter = WeatherAdapter(weather)
        }
    }

}