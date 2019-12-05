package com.geekhub.mariia_piastro.hw5.weather.entities

import com.squareup.moshi.Json
import java.io.Serializable

data class MainWeatherInfo(
    val temp: Double,
    val humidity: Double,
    val pressure: Double,
    @Json(name = "temp_min") val tempMin: Double = temp,
    @Json(name = "temp_max") val tempMax: Double = temp
): Serializable