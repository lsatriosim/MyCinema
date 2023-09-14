package com.example.mycinema.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.core.domain.model.Movie
import com.example.core.ui.MovieCard
import com.example.mycinema.favorite.navigation.Screen
import com.example.mycinema.favorite.ui.FavoriteViewModel

@Composable
fun FavoriteScreen(favoriteViewModel: FavoriteViewModel, navController: NavHostController){
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "My Favorite Movies", color = Color.White) },
            backgroundColor = Color("#1FA4BB".toColorInt())
        )
    }) {innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colors.background
        ) {
            FavoriteList(movieList = favoriteViewModel.movieState.collectAsState().value, navController)
        }
    }
}

@Composable
fun FavoriteList(movieList: List<Movie>, navController: NavHostController){
    LazyColumn{
        items(movieList, key = {it.id}){movie ->
            MovieCard(movie = movie, onClick = {
                navController.navigate(Screen.movieDetail.createRoute(movie.id))
            })
        }
    }
}