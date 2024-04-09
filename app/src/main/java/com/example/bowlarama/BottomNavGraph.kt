package com.example.bowlarama

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bowlarama.screens.BookScreen
import com.example.bowlarama.screens.ContactScreen
import com.example.bowlarama.screens.HomeScreen
import com.example.bowlarama.screens.TeamsScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Teams.route) {
            TeamsScreen()
        }
        composable(route = BottomBarScreen.Book.route) {
            BookScreen()
        }
        composable(route = BottomBarScreen.Contact.route) {
            ContactScreen()
        }
    }
}
