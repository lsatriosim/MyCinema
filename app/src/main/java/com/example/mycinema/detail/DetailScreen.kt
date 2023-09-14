package com.example.mycinema.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.core.R
import com.example.core.navigation.Screen

@Composable
fun DetailScreen(detailViewModel: DetailViewModel, navController: NavHostController, movieId: Int) {
    detailViewModel.getDetailMovie(movieId)
    val movie = detailViewModel.movieState.collectAsState().value
    detailViewModel.getIsFavorite()

    val scrollState = rememberLazyListState()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = movie.title, color = Color.White) },
            backgroundColor = Color("#1FA4BB".toColorInt()),
        )
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = scrollState
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                    Image(
                        painter = rememberImagePainter("https://image.tmdb.org/t/p/original${movie.posterPath}"),
                        contentDescription = "Foto Poster",
                        modifier = Modifier
                            .padding(16.dp)
                            .width(180.dp)
                            .height(320.dp)
                    )
                }
            }
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(text = movie.title, fontWeight = FontWeight.Bold)
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Text(text = movie.overview)
            }
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
            item {
                Text(text = "Vote Average: ${movie.voteAverage}")
            }
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
            item {
                Text(text = "Vote Count: ${movie.popularity}")
            }
            item {
                Spacer(modifier = Modifier.height(4.dp))
            }
            item {
                Text(text = "Popularity: ${movie.voteCount}")
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                val buttonText = when (detailViewModel.isFavoriteState.collectAsState().value) {
                    true -> "Remove from Favorite"
                    false -> "Add to Favorite"
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(onClick = { detailViewModel.setFavoriteMovie(movie, !movie.isFavorite) }) {
                        Text(text = buttonText)
                    }
                }
            }
        }
    }
}