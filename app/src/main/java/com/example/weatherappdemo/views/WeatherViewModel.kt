package com.example.weatherappdemo.views

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherappdemo.model.PojoCurrentWeather
import com.example.weatherappdemo.repo.WeatherRepo

class WeatherViewModel(app:Application) :AndroidViewModel(app){
    private var repository: WeatherRepo? = null

    init {
        repository = WeatherRepo(app)
    }

    fun getCurrentWeatherLiveData(): MutableLiveData<PojoCurrentWeather> {
        return repository!!.currentWeatherLiveData
    }

    fun getCurrentWeather(query:String) {
        return repository!!.getCurrentWeather(query)
    }

}