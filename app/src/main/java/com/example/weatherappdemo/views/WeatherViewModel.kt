package com.example.weatherappdemo.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherappdemo.model.PojoWeatherForecast
import com.example.weatherappdemo.repo.WeatherRepo

class WeatherViewModel :ViewModel(){
    private var repository: WeatherRepo? = null

    init {
        repository = WeatherRepo()
    }

    fun getWeatherForecast(query: String){
        return repository!!.get7DaysWeatherForecast(query)
    }

    fun getWeatherForecastLiveData(): MutableLiveData<PojoWeatherForecast> {
        return repository!!.forecastWeatherLiveData
    }

    fun getWeatherForecastErrorLiveData(): MutableLiveData<String> {
        return repository!!.forecastWeatherErrorLiveData
    }
}