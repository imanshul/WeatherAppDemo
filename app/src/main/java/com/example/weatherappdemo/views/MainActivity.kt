package com.example.weatherappdemo.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherappdemo.R

class MainActivity : AppCompatActivity() {

    var viewModel: WeatherViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun checkAPICall() {
        if (viewModel != null) return

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
    }
}
