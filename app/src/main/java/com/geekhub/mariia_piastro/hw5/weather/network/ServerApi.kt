package com.geekhub.mariia_piastro.hw5.weather.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("units") units: String
    ): Call<WeatherResponse>
}