package com.practice.weatherapp.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.practice.weatherapp.data.DataOrException
import com.practice.weatherapp.model.Weather
import com.practice.weatherapp.utils.formatDate
import com.practice.weatherapp.utils.formatDateTime
import com.practice.weatherapp.widgets.SearchAppBar

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true),
    ) {
        value = mainViewModel.getWeatherData("Rajshahi")
    }.value

    if (weatherData.loading == true) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

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
        ) {
            Log.d("TAG", "MainScaffold: Button Clicked")
        }
    }) { innerPadding ->
        MainContent(weather, innerPadding)
    }

}


@Composable
fun MainContent(weather: Weather, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = formatDate(weather.list[0].dt), modifier = Modifier.padding(6.dp))

        Surface(
            modifier = Modifier
                .size(210.dp)
                .padding(4.dp),
            shape = CircleShape,
            color = Color(0xFFFFE082)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //Weather Image
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${weather.list[0].weather[0].icon}.png",
                    contentDescription = "Weather Image",
                    modifier = Modifier.size(95.dp)
                )

                Text(
                    text = weather.list[0].main.temp.toInt().toString() + "°",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
                Text(
                    text = weather.list[0].weather[0].main,
                    modifier = Modifier.padding(6.dp),
                    fontStyle = FontStyle.Italic,
                    color = Color.DarkGray,
                    fontSize = 18.sp
                )
            }
        }
    }
}