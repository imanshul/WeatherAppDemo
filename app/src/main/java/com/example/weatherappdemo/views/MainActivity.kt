package com.example.weatherappdemo.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappdemo.R
import com.example.weatherappdemo.extensions.showMessage
import com.example.weatherappdemo.model.ListForecastData
import com.example.weatherappdemo.model.PojoCurrentWeather
import com.example.weatherappdemo.utils.AnimationUtils
import com.example.weatherappdemo.utils.DateTimeUtils
import com.example.weatherappdemo.utils.InternetCheckUtils
import com.example.weatherappdemo.utils.OnItemClick
import com.example.weatherappdemo.views.adapter.WeatherForecastAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_details.*
import kotlinx.android.synthetic.main.item_retry.*
import java.util.*
import java.util.Calendar.getInstance
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), OnItemClick {

    var viewModel: WeatherViewModel? = null

    val adapter by lazy { WeatherForecastAdapter(this) }

    var stringToSearch = "Mohali"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hideRetryView()
        setAdapter()
        addObserver()
    }

    private fun setAdapter() {
        rvWeatherForecast.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvWeatherForecast.adapter = adapter
    }

    private fun addObserver() {
        if (viewModel != null) return

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        getWeatherData()

        viewModel?.getCurrentWeatherLiveData()?.observe(this, Observer {
            hideRetryView()
            setCurrentWeatherData(it)
        })

        viewModel?.getCurrentWeatherErrorLiveData()?.observe(this, Observer {
            showMessage(this, "Error : $it")
            showRetryView()
        })

        viewModel?.getWeatherForecastLiveData()?.observe(this, Observer {
            it?.apply {
                val list = get5DayForecast(this.listForecastData)
                adapter.addData(list)
            }
        })

        viewModel?.getWeatherForecastErrorLiveData()?.observe(this, Observer {
            showMessage(this, "Error : $it")
        })

    }

    private fun showRetryView() {
        tvCurrentLocation.visibility = View.GONE
        cardRetry.visibility = View.VISIBLE
        clCurrentWeather.visibility = View.GONE
    }

    private fun hideRetryView() {
        tvCurrentLocation.visibility = View.VISIBLE
        cardRetry.visibility = View.GONE
        clCurrentWeather.visibility = View.VISIBLE
    }


    private fun getWeatherData() {
        if (InternetCheckUtils.isNetworkAvailable(this)) {
            viewModel?.getCurrentWeather(stringToSearch)
            viewModel?.getWeatherForecast(stringToSearch)
        } else {
            showMessage(this, "No Internet Available!")
            showRetryView()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchViewItem: MenuItem = menu!!.findItem(R.id.app_bar_search)
        val searchView: SearchView = searchViewItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //   viewModel?.getCurrentWeather(query.toString())MenuItemCompat.collapseActionView(searchMenuItem);
                stringToSearch = query.toString()
                getWeatherData()
                searchViewItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentWeatherData(it: PojoCurrentWeather?) {
        it?.apply {
            tvCurrentLocation.text = "${getString(R.string.location)} $name"
            tvCurrentTemp.text = main?.temp?.toInt().toString()
            tvWeatherDescription.text = weather?.get(0)?.main ?: ""
            tvSunriseTime.text = DateTimeUtils.getTimeFromMillis(sys?.sunrise) ?: ""
            tvSunsetTime.text = DateTimeUtils.getTimeFromMillis(sys?.sunset) ?: ""
            tvRealFeel.text = main?.feelsLike?.toInt().toString()
            tvCloudiness.text = "${clouds?.all}%"
            tvHumidity.text = "${main?.humidity}%"
            tvWind.text = "${wind?.speed} km/hr"
            if (!weather.isNullOrEmpty()) {
                animCurrentWeather.setAnimation(AnimationUtils.getWeatherAnimation(weather!!.get(0)?.id!!))
                animCurrentWeather.playAnimation()
            }
        }
    }

    override fun onItemClick(any: Any) {
        when (any) {
            is String -> {
                viewModel?.getWeatherForecast(stringToSearch)
            }
        }
    }

    /*
    * Search for 5 days forecast from 3hour forecast for 5 days
    * */
    private fun get5DayForecast(listForecastData: List<ListForecastData>?): List<ListForecastData> {
        val list = ArrayList<ListForecastData>()
        //Get current cal instance
        val futureCal = getInstance()
        //Get tomorrow cal instance
        futureCal.add(Calendar.DATE, 1)

        /*
        * Loop list to get only future 5 days data
        * */
        listForecastData?.forEach {
            val cal: Calendar = getInstance()
            cal.setTimeInMillis(it.dt!! * 1000)

            // Compare day of month with other date day of month
            if (futureCal.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {
                list.add(it)
                //Increment future date to get only list of future forecast
                futureCal.add(Calendar.DATE, 1)
            }
        }
        return list
    }
}