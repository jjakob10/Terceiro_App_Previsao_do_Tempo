package com.example.previsodotempo.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.previsodotempo.R
import com.example.previsodotempo.databinding.ActivityDetailsScreenBinding
import com.example.previsodotempo.util.WeatherInfo
import com.example.previsodotempo.viewModel.DetailsViewModel
import coil.load

class DetailsScreen : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailsScreenBinding;
    private lateinit var mainVM: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(DetailsViewModel::class.java)

        setObserver()
        binding.buttonBack.setOnClickListener(this)

        val wi = intent.getSerializableExtra("weatherInfo") as WeatherInfo
        mainVM.fillContent(wi)
    }

    private fun setObserver() {
        mainVM.getIconUrl().observe(this, Observer {

            val imageView = findViewById<ImageView>(R.id.imageView)
            imageView.load(it) {
                crossfade(true) // Optional: add a crossfade animation
                placeholder(R.drawable.placeholder_image) // Optional: show a placeholder
                error(R.drawable.error_image) // Optional: show an error image
            }
        })

        mainVM.getCityName().observe(this, Observer {
           binding.cityName.text =  "$it"
        })

        mainVM.getPrimaryInfo().observe(this, Observer {
            binding.primaryInfo.text =  "${mainVM.temp}${getString(R.string.tempUnity)}, ${mainVM.desc}"
        })

        mainVM.getMaxTemp().observe(this, Observer {
            binding.maxTemp.text =  "${getString(R.string.max_temp)}: $it${getString(R.string.tempUnity)}"
        })

        mainVM.getMinTemp().observe(this, Observer {
            binding.minTemp.text =  "${getString(R.string.min_temp)}: $it${getString(R.string.tempUnity)}"
        })
        mainVM.getFeelsLike().observe(this, Observer {
            binding.feelsLike.text =  "${getString(R.string.feels_like)}: $it${getString(R.string.tempUnity)}"
        })
        mainVM.getWindSpeed().observe(this, Observer {
            binding.windSpeed.text =  "${getString(R.string.wind_speed)}: $it ${getString(R.string.speedUnit)}"
        })
        mainVM.getHumidity().observe(this, Observer {
            binding.humidity.text =  "${getString(R.string.humidity)}: ${it}%"
        })
    }


    override fun onClick(view: View) {
        if (view.id == R.id.buttonBack) {
            finish()
        }
    }
}

