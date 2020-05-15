package com.dark_matter.pabri.ad340

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast(zipcode: String){
        val randomValues = List(7){ Random.nextFloat().rem(100) * 100 }
        val forecastItems = randomValues.map {temp ->
            DailyForecast(temp, getTempDescription(temp))
        }
        _weeklyForecast.setValue(forecastItems)
    }

    private fun getTempDescription(temp: Float) : String {
        return when (temp){
            in Float.MIN_VALUE.rangeTo(0f) -> "Anything below 0 doesn't make sense"
            in 0f.rangeTo(32f) -> "Snowpocalypse"
            in 32f.rangeTo(55f) -> "Cold but bearable"
            in 55f.rangeTo(75f) -> "Chilly and perfect"
            in 75f.rangeTo(85f) -> "Just the way I like it"
            in 85f.rangeTo(100f) -> "Ahh summer!!!"
            in 100f.rangeTo(110f) -> "Oh hell no, I'm moving to Alaska"
            else -> "Does not Compute"
        }
    }
}