package com.example.weatherappdemo.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demoappkissan.retrofit.RetrofitFactory
import com.example.weatherappdemo.constants.AppConstants
import com.example.weatherappdemo.model.PojoCurrentWeather
import com.example.weatherappdemo.model.PojoWeatherForecast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherRepo(app: Application) {

    val currentWeatherLiveData = MutableLiveData<PojoCurrentWeather>()
    val currentWeatherErrorLiveData = MutableLiveData<String>()
    fun getCurrentWeather(query: String) {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getCurrentWeather(
                    query,
                    AppConstants.DEFAULT_UNIT,
                    AppConstants.API_KEY
                )

                if (response.isSuccessful) {
                    //Do something with response e.g show to the UI.
                    currentWeatherLiveData.value = response.body()
                    Log.d("anshul", "Success 1: ${response.body()}")
                } else {
                    currentWeatherErrorLiveData.value = response.message()
                    Log.d("anshul", "Error 1: ${response.code()}")
                }
            } catch (e: Exception) {
                currentWeatherErrorLiveData.value = e.message
                Log.d("anshul", "Ooops: Something else went wrong ${e.message}")
            }
        }
    }


    val forecastWeatherLiveData = MutableLiveData<PojoWeatherForecast>()
    val forecastWeatherErrorLiveData = MutableLiveData<String>()
    fun get7DaysWeatherForecast(query: String,days:Int) {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getSevenDaysWeatherForecast(
                    query,
                    AppConstants.DEFAULT_UNIT,
                    days,
                    AppConstants.API_KEY
                )

                if (response.isSuccessful) {
                    //Do something with response e.g show to the UI.
                    forecastWeatherLiveData.value = response.body()
                    Log.d("anshul", "Success 1: ${response.body()}")
                } else {
                    forecastWeatherErrorLiveData.value = response.message()
                    Log.d("anshul", "Error 1: ${response.code()}")
                }
            } catch (e: Exception) {
                forecastWeatherErrorLiveData.value = e.message
                Log.d("anshul", "Ooops: Something else went wrong ${e.message}")
            }
        }
    }
}