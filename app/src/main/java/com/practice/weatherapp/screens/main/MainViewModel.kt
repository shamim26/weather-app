package com.practice.weatherapp.screens.main

import androidx.lifecycle.ViewModel
import com.practice.weatherapp.data.DataOrException
import com.practice.weatherapp.model.Weather
import com.practice.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    suspend fun getWeatherData(city: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city)
    }
}