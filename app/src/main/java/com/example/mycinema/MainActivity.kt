package com.example.mycinema

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.domain.model.Movie
import com.example.mycinema.home.HomeViewModel
import com.example.mycinema.ui.theme.MyCinemaTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.material.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core.ui.MovieCard
import com.example.core.navigation.Screen
import com.example.mycinema.detail.DetailScreen
import com.example.mycinema.detail.DetailViewModel
import com.example.mycinema.home.HomeScreen

class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModel()
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCinemaTheme {
                val navHostController: NavHostController = rememberNavController()
                // A surface container using the 'background' color from the theme
                NavHost(navController = navHostController, startDestination = Screen.home.route){
                    composable(Screen.home.route){
                        HomeScreen(homeViewModel = homeViewModel, navController = navHostController)
                    }
                    composable(Screen.favorite.route){
                        val uri = Uri.parse("mycinema://favorite")
                        startActivity(Intent(Intent.ACTION_VIEW, uri))
                    }
                    composable(
                        route = Screen.movieDetail.route,
                        arguments = listOf(navArgument("movieId"){
                            type = NavType.IntType
                        })
                    ){
                        val movieId = it.arguments?.getInt("movieId")
                        DetailScreen(detailViewModel = detailViewModel, navController = navHostController,
                            movieId!!
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyCinemaTheme {
        Greeting("Android")
    }
}

@Composable
fun MovieCardList(resource: com.example.core.data.Resource<List<Movie>>, navController: NavHostController){
    when(resource){
        is com.example.core.data.Resource.Loading -> {
            Text(text = "Loading for the movie")
        }
        is com.example.core.data.Resource.Error -> {
            Text(text = "Failed to load the data")
            Log.e("MovieCardList", resource.toString())
        }
        is com.example.core.data.Resource.Success -> {
            LazyColumn{
                items(resource.data!!, key = {it.id}){movie ->
                    MovieCard(movie = movie, onClick = {movieId ->
                        navController.navigate(Screen.movieDetail.createRoute(movieId))
                    })
                }
            }
        }
    }
}