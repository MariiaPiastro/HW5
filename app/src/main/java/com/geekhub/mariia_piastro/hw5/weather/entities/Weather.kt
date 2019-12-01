package com.geekhub.mariia_piastro.hw5.weather.entities

data class Weather(
    val id: Long,
    val main: String,
    val descriptor: String,
    val icon: String
)