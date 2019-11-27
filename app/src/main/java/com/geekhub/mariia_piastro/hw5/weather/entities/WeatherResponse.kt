package com.geekhub.mariia_piastro.hw5.weather.entities

import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
data class WeatherResponse(
    val id: Long,
    val main: MainWeatherInfo,
    val weather: List<Weather>,
    val wind: Wind
)