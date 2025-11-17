package com.example.previsodotempo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.previsodotempo.util.WeatherInfo

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    var temp: Double = 0.0
    var desc: String = ""
    private var cityName = MutableLiveData<String>()
    fun getCityName(): LiveData<String> {
        return cityName
    }

    private var primary_info = MutableLiveData<String>()
    fun getPrimaryInfo(): LiveData<String> {
        return primary_info
    }


    private var minTemp = MutableLiveData<Double>()
    fun getMinTemp(): LiveData<Double> {
        return minTemp
    }

    private var maxTemp = MutableLiveData<Double>()
    fun getMaxTemp(): LiveData<Double> {
        return maxTemp
    }

    private var feelsLike = MutableLiveData<Double>()
    fun getFeelsLike(): LiveData<Double> {
        return feelsLike
    }

    private var windSpeed = MutableLiveData<Double>()
    fun getWindSpeed(): LiveData<Double> {
        return windSpeed
    }

    private var humidity = MutableLiveData<Int>()
    fun getHumidity(): LiveData<Int> {
        return humidity
    }

    private var iconUrl = MutableLiveData<String>()
    fun getIconUrl(): LiveData<String> {
        return iconUrl
    }
    fun fillContent(wi: WeatherInfo) {
        cityName.postValue(wi.cityName)
        temp = wi.temp
        desc = wi.description
        primary_info.postValue("${wi.temp}, ${wi.description}")
        minTemp.postValue(wi.minTemp)
        maxTemp.postValue(wi.maxTemp)
        feelsLike.postValue(wi.feelsLike)
        windSpeed.postValue(wi.windSpeed)
        humidity.postValue(wi.humidity)
        iconUrl.postValue(wi.iconUrl)
    }
}