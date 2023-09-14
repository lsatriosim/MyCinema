package com.example.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.core.domain.model.Movie
import com.example.mycinema.ui.theme.MyCinemaTheme

@Composable
fun MovieCard(movie: Movie, onClick : (Int) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp)).padding(15.dp).clickable { onClick(movie.id) },
    elevation = 8.dp) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter("https://image.tmdb.org/t/p/original${movie.posterPath}"),
                contentDescription = movie.title,
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = movie.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = movie.overview, style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rating: ${movie.voteAverage}", style = MaterialTheme.typography.caption)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieCardPreview(){
    val movieData = Movie(
        1,
        "Elemental",
        "Elemental is a film which",
        "https://emi9d8rzue7.exactdn.com/wp-content/uploads/2022/09/Elemental-Poster.jpg?strip=all&lossy=1&w=672&ssl=1",
        "https://emi9d8rzue7.exactdn.com/wp-content/uploads/2022/09/Elemental-Poster.jpg?strip=all&lossy=1&w=672&ssl=1",
        198.0,
        9.8,
        1234,
        false
    )
    MyCinemaTheme {
        MovieCard(movie = movieData, {})
    }
}