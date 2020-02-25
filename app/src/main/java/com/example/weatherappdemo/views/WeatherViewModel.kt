package com.example.weatherappdemo.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherappdemo.model.PojoCurrentWeather
import com.example.weatherappdemo.model.PojoWeatherForecast
import com.example.weatherappdemo.repo.WeatherRepo

class WeatherViewModel(app:Application) :AndroidViewModel(app){
    private var repository: WeatherRepo? = null

    init {
        repository = WeatherRepo(app)
    }

    fun getCurrentWeatherLiveData(): MutableLiveData<PojoCurrentWeather> {
        return repository!!.currentWeatherLiveData
    }

    fun getCurrentWeatherErrorLiveData(): MutableLiveData<String> {
        return repository!!.currentWeatherErrorLiveData
    }

    fun getCurrentWeather(query:String) {
        return repository!!.getCurrentWeather(query)
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