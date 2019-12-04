package com.geekhub.mariia_piastro.hw5.weather.entities

import com.squareup.moshi.Json
import java.io.Serializable

data class WeatherResponse(
    val main: MainWeatherInfo,
    val weather: List<Weather>,
    val wind: Wind,
    @Json(name = "dt_txt") val date: String
): Serializable