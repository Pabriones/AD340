package com.dark_matter.pabri.ad340.api

import com.squareup.moshi.Json
import java.util.*

data class WeatherDescription(val main: String, val description: String, val icon: String)

data class Temp (val main: Float, val max: Float)

data class DailyForecast (
    @field:Json(name = "dt") val date: Long,
    val temp: Temp,
    val weather: List<WeatherDescription>
)

data class WeeklyForecast (val daily: List<DailyForecast>)