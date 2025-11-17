package com.example.previsodotempo.ui


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.previsodotempo.R
import com.example.previsodotempo.databinding.ActivityMainBinding
import com.example.previsodotempo.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)

        setObserver()
        binding.buttonVerPrevisao.setOnClickListener(this)
    }

    private fun setObserver() {
        mainVM.getCityNotFound().observe(this, Observer {
            Toast.makeText(applicationContext, "${getString(R.string.cityNotFound)}: ${mainVM.getCityName()}", Toast.LENGTH_SHORT).show()
        })

        mainVM.getApiError().observe(this, Observer {
            Toast.makeText(applicationContext, "${getString(R.string.apiErro)}: ${mainVM.getApiErrorMsg()}", Toast.LENGTH_SHORT).show()
        })

        mainVM.getWeatherInfo().observe(this, Observer {
            val intent = Intent(this, DetailsScreen::class.java)
            val wi = mainVM.getWeatherInfoObj()

            val bundle = bundleOf(
                "weatherInfo" to wi

            )

            intent.putExtras(bundle)
            startActivity(intent)
        })
    }


    override fun onClick(view: View) {
        if (view.id == R.id.button_ver_previsao) {
            val nomeCidade = binding.EditTextNome.text.toString()
            if (nomeCidade == "") {
                Toast.makeText(applicationContext, R.string.nome_valido, Toast.LENGTH_SHORT).show()
            }
            else {
                mainVM.getCityInfo(nomeCidade, getString(R.string.unit), getString(R.string.country_code))
            }

        }
    }

}



