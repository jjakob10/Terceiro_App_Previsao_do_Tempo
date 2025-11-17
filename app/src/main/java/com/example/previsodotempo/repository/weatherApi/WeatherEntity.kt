package com.example.previsodotempo.repository.weatherApi

import com.google.gson.annotations.SerializedName

class WeatherEntity {
    @SerializedName("message")
    var message: String = ""

    @SerializedName("cod")
    var cod:Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("weather")
    var weather: List<Weather> =  emptyList()

    @SerializedName("main")
    var main: Main? = null

    @SerializedName("wind")
    var wind: Wind? = null

}

class Weather{
    @SerializedName("description")
    var description: String = ""

    @SerializedName("icon")
    var icon: String = ""
}

class Main{

    @SerializedName("temp")
    var temp: Double = 0.0

    @SerializedName("feels_like")
    var feelsLike: Double = 0.0

    @SerializedName("temp_min")
    var tempMin: Double = 0.0

    @SerializedName("temp_max")
    var tempMax: Double = 0.0

    @SerializedName("humidity")
    var humidity: Int = 0
}
class  Wind{
    @SerializedName("speed")
    var speed: Double = 0.0
}



