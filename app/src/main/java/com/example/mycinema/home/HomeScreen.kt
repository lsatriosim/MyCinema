package com.example.mycinema.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.mycinema.MovieCardList
import com.example.core.navigation.Screen
import com.example.core.R

@Composable
fun HomeScreen(homeViewModel:HomeViewModel, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "MyCinema", color = Color.White) },
                backgroundColor = Color("#1FA4BB".toColorInt()),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.favorite.route) }) {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_favorite_filled), contentDescription = "favorite button", tint = Color.Red)
                    }
                }
            )
        },
    ) {innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colors.background
        ) {
            MovieCardList(resource = homeViewModel.movieState.collectAsState().value, navController)
        }
    }
}