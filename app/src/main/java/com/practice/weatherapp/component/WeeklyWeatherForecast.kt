package com.practice.weatherapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.practice.weatherapp.model.Weather
import com.practice.weatherapp.model.WeatherObject
import com.practice.weatherapp.utils.formatDate
import com.practice.weatherapp.utils.formatDecimals
import com.practice.weatherapp.utils.getDailyForecast

@Composable
fun WeeklyWeatherForecast(weather: Weather) {
    val filteredForecast = getDailyForecast(weather.list)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(5.dp)
        ) {
        LazyColumn() {
            items(filteredForecast.size) { index ->
                WeatherDetailRow(weatherDetailsData = filteredForecast[index])
            }
        }
    }
}

@Composable
fun WeatherDetailRow(weatherDetailsData: WeatherObject) {
    Surface(modifier = Modifier.padding(3.dp), shape = RoundedCornerShape(5.dp)) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = formatDate(weatherDetailsData.dt).split(",")[0])
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${weatherDetailsData.weather[0].icon}.png",
                contentDescription = "Weather Image",
                modifier = Modifier.size(50.dp)
            )
            Text(text = weatherDetailsData.weather[0].description)
            Text(text = formatDecimals(weatherDetailsData.main.temp) + "°")
        }
    }

}