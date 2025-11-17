package com.example.previsodotempo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.previsodotempo.BuildConfig
import com.example.previsodotempo.repository.weatherApi.ClientRetrofit
import com.example.previsodotempo.repository.weatherApi.WeatherEntity
import com.example.previsodotempo.repository.weatherApi.WeatherService
import com.example.previsodotempo.util.WeatherInfo
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(application: Application) : AndroidViewModel(application) {


    val weatherApi = ClientRetrofit.createService(WeatherService::class.java)

    private var weatherInfo = MutableLiveData<WeatherEntity>()

    private var cityNotFound = MutableLiveData<String>()

    private var apiError = MutableLiveData<String>()

    fun getCityNotFound(): LiveData<String> {
        return cityNotFound
    }
    fun getWeatherInfo(): LiveData<WeatherEntity> {
        return weatherInfo
    }

    fun getWeatherInfoObj(): WeatherInfo {

        val iconURL = "https://openweathermap.org/img/wn/${weatherInfo.value?.weather[0]?.icon}@2x.png"

        val wi = WeatherInfo(
            weatherInfo.value?.name ?: "",
            weatherInfo.value?.weather[0]?.description ?: "",
            weatherInfo.value?.main?.temp ?: 0.0,
            weatherInfo.value?.main?.tempMin ?: 0.0,
            weatherInfo.value?.main?.tempMax?:0.0,
            weatherInfo.value?.main?.feelsLike?:0.0,
            weatherInfo.value?.wind?.speed?:0.0,
            weatherInfo.value?.main?.humidity?:0,
            iconURL

        )

        return wi
    }

    fun getApiError(): LiveData<String> {
       return apiError
    }

    fun getApiErrorMsg(): String {
        return apiError.value!!
    }
    fun getCityInfo(n: String, unit: String, lang: String){
        val resp: Call<WeatherEntity> = weatherApi.getWeatherInfo(n,
            BuildConfig.API_KEY,lang, unit)


        resp.enqueue(object : Callback<WeatherEntity> {

            override fun onResponse(
                call: Call<WeatherEntity>,
                response: Response<WeatherEntity>
            ) {

                if (response.isSuccessful) {
                    if( response.body()?.cod == 200) {

                        weatherInfo.value = response.body()
                        return

                    }
                }

                cityNotFound.value = n
            }

            override fun onFailure(call: Call<WeatherEntity>, t: Throwable) {
                apiError.value = "${t.localizedMessage}"
            }
        })
    }

    fun getCityName(): String{
        return cityNotFound.value!!
    }



}