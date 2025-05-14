package com.example.hitech.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hitech.features.home.ui.HomePage
import com.example.hitech.features.map.ui.MapPage

@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
         composable("home"){
             HomePage()
         }
        composable("map"){
            MapPage()
        }
    }
}