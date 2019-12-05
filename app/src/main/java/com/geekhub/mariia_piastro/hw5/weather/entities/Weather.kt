package com.geekhub.mariia_piastro.hw5.weather.entities

import java.io.Serializable

data class Weather(
    val main: String,
    val description: String,
    val icon: String
): Serializable