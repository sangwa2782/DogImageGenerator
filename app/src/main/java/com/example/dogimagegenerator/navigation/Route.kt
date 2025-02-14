package com.example.dogimagegenerator.navigation

sealed class Route(val route: String) {

    object HomeRoute : Route("home")
    object ImageGeneratorRoute : Route("generate")
    object RecentImageRoute : Route("recent")

}