package com.practice.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practice.weatherapp.screens.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController, WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            SplashScreen(navController)
        }
    }
} 