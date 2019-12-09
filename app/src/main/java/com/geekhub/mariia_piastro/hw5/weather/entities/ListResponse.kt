package com.geekhub.mariia_piastro.hw5.weather.entities

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ListResponse(val list: List<WeatherResponse>) : Serializable