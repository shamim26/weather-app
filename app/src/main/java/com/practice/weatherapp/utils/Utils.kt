package com.practice.weatherapp.utils

import com.practice.weatherapp.model.WeatherObject
import java.text.SimpleDateFormat

fun formatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm aa")
    val date = java.util.Date(timestamp.toLong() * 1000)
    return sdf.format(date)
}

fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}

fun getDailyForecast(forecastList: List<WeatherObject>): List<WeatherObject> {
    val grouped = mutableMapOf<String, WeatherObject>()

    forecastList.forEach { item ->
        val date = item.dt_txt.substring(0, 10) // "2025-09-25"
        // pick only one per day (e.g., 12:00:00)
        if (item.dt_txt.contains("12:00:00") && !grouped.containsKey(date)) {
            grouped[date] = item
        }
    }

    return grouped.values.take(7).toList()
}