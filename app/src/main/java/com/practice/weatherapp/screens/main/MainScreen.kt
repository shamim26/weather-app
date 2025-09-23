package com.practice.weatherapp.screens.main

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.practice.weatherapp.data.DataOrException
import com.practice.weatherapp.model.Weather
import com.practice.weatherapp.widgets.SearchAppBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true),
    ) {
        value = mainViewModel.getWeatherData("Naogaon")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weather = weatherData.data!!, navController = navController)
    }


}

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {

    Scaffold(topBar = {
        SearchAppBar(
            navController = navController,
            title = weather.city.name + ", ${weather.city.country}",
        ){
            Log.d("TAG", "MainScaffold: Button Clicked")
        }
    }) { innerPadding ->
        MainContent(weather, innerPadding)
    }

}


@Composable
fun MainContent(weather: Weather, innerPadding: PaddingValues) {
    Text(text = weather.city.name, modifier = Modifier.padding(innerPadding))
}