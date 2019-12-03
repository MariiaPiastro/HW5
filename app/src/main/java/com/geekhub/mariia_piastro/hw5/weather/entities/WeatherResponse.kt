package com.geekhub.mariia_piastro.hw5.weather.entities

data class WeatherResponse(
    val id: Long,
    val main: MainWeatherInfo,
    val weather: List<Weather>,
    val wind: Wind
)