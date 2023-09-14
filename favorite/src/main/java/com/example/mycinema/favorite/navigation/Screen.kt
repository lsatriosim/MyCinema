package com.example.mycinema.favorite.navigation

sealed class Screen(val route:String) {
    object home : Screen("home")

    object favorite: Screen("favorite")

    object movieDetail: Screen("movieDetail/{movieId}"){
        fun createRoute(movieId: Int) ="movieDetail/${movieId}"
    }
}