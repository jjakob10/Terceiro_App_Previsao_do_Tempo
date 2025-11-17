package com.example.previsodotempo.repository.weatherApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    @GET("data/2.5/weather")
    fun getWeatherInfo(@Query("q") cityName: String,
                       @Query("appid") apiKey: String,
                        @Query("lang") lang:String = "pt_br",
                        @Query("units") unit: String ="metric"
                        ):Call<WeatherEntity>
}