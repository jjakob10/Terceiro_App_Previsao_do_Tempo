package com.example.previsodotempo.util

import java.io.Serializable

data class WeatherInfo(
    val cityName: String,
    val description: String,
    val temp: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val feelsLike: Double,
    val windSpeed: Double,
    val humidity: Int,
    val iconUrl: String
) : Serializable
