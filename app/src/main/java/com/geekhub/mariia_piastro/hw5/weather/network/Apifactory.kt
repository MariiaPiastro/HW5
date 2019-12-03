package com.geekhub.mariia_piastro.hw5.weather.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.geekhub.mariia_piastro.hw5.weather.BuildConfig
import com.geekhub.mariia_piastro.hw5.weather.MainApplication
import com.geekhub.mariia_piastro.hw5.weather.entities.ListResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Apifactory {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(ChuckerInterceptor(MainApplication.applicationContext()))
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)

    fun getCurrentWeather(location: String, units: String): Call<ListResponse> {
        return weatherApi.getCurrentWeather(location, units)
    }

}