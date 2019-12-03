package com.geekhub.mariia_piastro.hw5.weather.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListResponse(val responses: List<WeatherResponse>)