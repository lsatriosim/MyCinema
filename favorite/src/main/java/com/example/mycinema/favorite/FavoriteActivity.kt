package com.example.mycinema.favorite

import androidx.appcompat.app.AppCompatActivity
import com.example.mycinema.favorite.ui.FavoriteViewModel
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycinema.detail.DetailScreen
import com.example.mycinema.detail.DetailViewModel
import com.example.mycinema.favorite.navigation.Screen
import org.koin.core.context.loadKoinModules
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(favoriteModule)
        super.onCreate(savedInstanceState)
        setContent{
            val navHostController: NavHostController = rememberNavController()
            NavHost(navController = navHostController, startDestination = Screen.favorite.route){
                composable(Screen.favorite.route){
                    FavoriteScreen(favoriteViewModel = favoriteViewModel, navHostController)
                }
                composable(
                    route = com.example.core.navigation.Screen.movieDetail.route,
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