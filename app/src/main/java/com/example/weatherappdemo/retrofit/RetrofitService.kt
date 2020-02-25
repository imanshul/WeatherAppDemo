package com.example.weatherappdemo.retrofit

import com.example.weatherappdemo.model.PojoCurrentWeather
import com.example.weatherappdemo.model.PojoWeatherForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") query: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Response<PojoCurrentWeather>


    @GET("forecast")
    suspend fun getSevenDaysWeatherForecast(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("appid") appId: String
        ): Response<PojoWeatherForecast>

}