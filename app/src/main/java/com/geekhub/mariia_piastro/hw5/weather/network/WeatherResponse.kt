package com.geekhub.mariia_piastro.hw5.weather.network

import com.geekhub.mariia_piastro.hw5.weather.entities.MainWeatherInfo
import com.geekhub.mariia_piastro.hw5.weather.entities.Weather
import com.geekhub.mariia_piastro.hw5.weather.entities.Wind

data class WeatherResponse(
    val id: Long,
    val main: MainWeatherInfo,
    val weather: List<Weather>,
    val wind: Wind
)