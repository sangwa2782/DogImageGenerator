package com.example.dogimagegenerator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dogimagegenerator.screen.GenerateDogsScreen
import com.example.dogimagegenerator.screen.HomeScreen
import com.example.dogimagegenerator.screen.RecentDogsScreen
import com.example.dogimagegenerator.viewModel.DogViewModel


@Composable
fun DogAppNaveController(navController: NavHostController, viewModel: DogViewModel) {
    NavHost(navController, startDestination = Route.HomeRoute.route) {
        composable(Route.HomeRoute.route) { HomeScreen(navController) }
        composable(Route.ImageGeneratorRoute.route) { GenerateDogsScreen( navController,viewModel) }
        composable(Route.RecentImageRoute.route) { RecentDogsScreen(navController,viewModel) }
    }
}