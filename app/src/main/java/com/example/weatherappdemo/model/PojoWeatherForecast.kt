package com.example.weatherappdemo.model

import com.google.gson.annotations.SerializedName


data class PojoWeatherForecast(
    @SerializedName("city")
    var city: City? = null,
    @SerializedName("cnt")
    var cnt: Int? = null,
    @SerializedName("cod")
    var cod: String? = null,
    @SerializedName("list")
    var listForecastData: List<ListForecastData>? = null,
    @SerializedName("message")
    var message: Int? = null
)

data class City(
    @SerializedName("coord")
    var coord: Coord? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("population")
    var population: Int? = null,
    @SerializedName("sunrise")
    var sunrise: Long? = null,
    @SerializedName("sunset")
    var sunset: Long? = null,
    @SerializedName("timezone")
    var timezone: Int? = null
)

data class Coord(
    @SerializedName("lat")
    var lat: Double? = null,
    @SerializedName("lon")
    var lon: Double? = null
)

data class ListForecastData(
    @SerializedName("clouds")
    var clouds: Clouds? = null,
    @SerializedName("dt")
    var dt: Long? = null,
    @SerializedName("dt_txt")
    var dtTxt: String? = null,
    @SerializedName("main")
    var main: Main? = null,
    @SerializedName("sys")
    var sys: Sys? = null,
    @SerializedName("weather")
    var weather: List<Weather?>? = null,
    @SerializedName("wind")
    var wind: Wind? = null
)

data class Clouds(
    @SerializedName("all")
    var all: Int? = null
)

data class Main(
    @SerializedName("feels_like")
    var feelsLike: Double? = null,
    @SerializedName("grnd_level")
    var grndLevel: Double? = null,
    @SerializedName("humidity")
    var humidity: Double? = null,
    @SerializedName("pressure")
    var pressure: Double? = null,
    @SerializedName("sea_level")
    var seaLevel: Double? = null,
    @SerializedName("temp")
    var temp: Double? = null,
    @SerializedName("temp_kf")
    var tempKf: Double? = null,
    @SerializedName("temp_max")
    var tempMax: Double? = null,
    @SerializedName("temp_min")
    var tempMin: Double? = null
)

data class Sys(
    @SerializedName("pod")
    var pod: String? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("sunrise")
    var sunrise: Long? = null,
    @SerializedName("sunset")
    var sunset: Long? = null
)

data class Weather(
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("icon")
    var icon: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("main")
    var main: String? = null
)

data class Wind(
    @SerializedName("deg")
    var deg: Double? = null,
    @SerializedName("speed")
    var speed: Double? = null
)