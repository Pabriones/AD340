package com.dark_matter.pabri.ad340

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeedittext: EditText = findViewById(R.id.zipcodeedittext)
        val enterButton: Button = findViewById(R.id.submitbutton)

        enterButton.setOnClickListener{
            val zipcode: String =zipcodeedittext.text.toString()

            if(zipcode.length != 5){
                Toast.makeText(this, R.string.zipcode_entry_error, Toast.LENGTH_SHORT).show()
            }else{
                forecastRepository.loadForecast(zipcode)
            }

            //Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
        }

        val forecastList: RecyclerView = findViewById(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(this)

        val dailyForecastAdapter = DailyForecastAdapter(){forecastItem ->
            val msg = getString(R.string.forecast_clicked_format, forecastItem.temp, forecastItem.description)
            Toast.makeText(this, msg,Toast.LENGTH_SHORT).show()
        }
        forecastList.adapter = dailyForecastAdapter

        val weeklyForecastObserver = Observer<List<DailyForecast>>{foreCastItems ->
            // update our list adapter
            dailyForecastAdapter.submitList(foreCastItems)
        }

        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)

    }
}
