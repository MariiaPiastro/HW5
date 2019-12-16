package com.geekhub.mariia_piastro.hw5.weather.fragments

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekhub.mariia_piastro.hw5.weather.MainApplication
import com.geekhub.mariia_piastro.hw5.weather.R
import com.geekhub.mariia_piastro.hw5.weather.activity.MainActivity
import com.geekhub.mariia_piastro.hw5.weather.database.WeatherContract
import com.geekhub.mariia_piastro.hw5.weather.database.WeatherDbHelper
import com.geekhub.mariia_piastro.hw5.weather.entities.*
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
        get() = PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext())

    private val dbHelper = WeatherDbHelper(MainApplication.applicationContext())
    val db = dbHelper.writableDatabase

    private lateinit var activity: Activity

    interface FirstItem {
        fun getFirstItem(weatherResponse: WeatherResponse)
    }

    companion object {
        fun newInstance(): ListFragment =
            ListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mItemClick = context as WeatherAdapter.ItemClick
        activity = context as Activity
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
        pref.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            if  (key == "location") {
                db.delete(WeatherContract.WeatherEntry.TABLE_NAME, null, null)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getWeather()
    }

    private fun getWeather() {
        val location = pref.getString("location", "Cherkasy") ?: "Cherkasy"
        val units = pref.getString("units", "metric") ?: "metric"
        Apifactory.getCurrentWeather(location, units)
            .enqueue(object : Callback<ListResponse> {

                override fun onResponse(
                    call: Call<ListResponse>,
                    response: Response<ListResponse>
                ) {
                    if (response.isSuccessful) {
                        db.delete(WeatherContract.WeatherEntry.TABLE_NAME, null, null)
                        for (content in response.body()?.list ?: emptyList()) {
                            insertWeather(content)
                        }
                        (activity as FirstItem).getFirstItem(getWeatherFromDb()[0])
                        weatherAdapter.setData(getWeatherFromDb())
                        weatherAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<ListResponse>, t: Throwable) {
                    Log.d("err", "ERR")
                    t.printStackTrace()
                    weatherAdapter.setData(getWeatherFromDb())
                    weatherAdapter.notifyDataSetChanged()
                }
            })
    }

    private fun insertWeather(weatherResponse: WeatherResponse) {

        val contentValues = ContentValues().apply {
            put(WeatherContract.WeatherEntry.COLUMN_TEMP, weatherResponse.main.temp)
            put(
                WeatherContract.WeatherEntry.COLUMN_HUMIDITY,
                weatherResponse.main.humidity
            )
            put(
                WeatherContract.WeatherEntry.COLUMN_PRESSURE,
                weatherResponse.main.pressure
            )
            put(
                WeatherContract.WeatherEntry.COLUMN_TEMP_MIN,
                weatherResponse.main.tempMin
            )
            put(
                WeatherContract.WeatherEntry.COLUMN_TEMP_MAX,
                weatherResponse.main.tempMax
            )
            put(
                WeatherContract.WeatherEntry.COLUMN_MAIN,
                weatherResponse.weather[0].main
            )
            put(
                WeatherContract.WeatherEntry.COLUMN_DESCRIPTION,
                weatherResponse.weather[0].description
            )
            put(
                WeatherContract.WeatherEntry.COLUMN_ICON,
                weatherResponse.weather[0].icon
            )
            put(
                WeatherContract.WeatherEntry.COLUMN_WIND_SPEED,
                weatherResponse.wind.speed
            )
            put(
                WeatherContract.WeatherEntry.COLUMN_WIND_DEGREE,
                weatherResponse.wind.deg
            )
            put(WeatherContract.WeatherEntry.COLUMN_DATE, weatherResponse.date)
        }
        db?.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, contentValues)
    }

    private fun getWeatherFromDb(): MutableList<WeatherResponse> {
        val weathers = mutableListOf<WeatherResponse>()
        val cursor = db.query(
            WeatherContract.WeatherEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        with(cursor) {
            while (moveToNext()) {
                val temp =
                    getDouble(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_TEMP))
                val humidity =
                    getDouble(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_HUMIDITY))
                val pressure =
                    getDouble(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_PRESSURE))
                val tempMin =
                    getDouble(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_TEMP_MIN))
                val tempMax =
                    getDouble(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_TEMP_MAX))
                val main =
                    getString(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_MAIN))
                val description =
                    getString(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_DESCRIPTION))
                val icon =
                    getString(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_ICON))
                val speed =
                    getDouble(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED))
                val degree =
                    getDouble(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_WIND_DEGREE))
                val date =
                    getString(getColumnIndexOrThrow(WeatherContract.WeatherEntry.COLUMN_DATE))
                val weatherResponse = WeatherResponse(
                    main = MainWeatherInfo(
                        temp,
                        humidity,
                        pressure,
                        tempMin,
                        tempMax
                    ),
                    weather = listOf(Weather(main, description, icon)),
                    wind = Wind(speed, degree), date = date
                )
                weathers.add(weatherResponse)
            }
        }
        cursor.close()
        return weathers
    }
}