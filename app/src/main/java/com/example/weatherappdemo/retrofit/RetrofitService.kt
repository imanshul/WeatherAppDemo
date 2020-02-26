package com.example.weatherappdemo.retrofit

import com.example.weatherappdemo.model.PojoWeatherForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    /*
    * To get 6 days forecast including current
    * */

    @GET("forecast")
    suspend fun getSixDaysWeatherForecast(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("appid") appId: String
        ): Response<PojoWeatherForecast>

}