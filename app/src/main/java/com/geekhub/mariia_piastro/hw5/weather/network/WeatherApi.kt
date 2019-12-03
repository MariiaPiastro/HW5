package com.geekhub.mariia_piastro.hw5.weather.network

import com.geekhub.mariia_piastro.hw5.weather.entities.ListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast?lang=ru")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("units") units: String
    ): Call<ListResponse>

}