package com.example.mycinema.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(val movieUseCase: MovieUseCase): ViewModel() {
    private val _movieState = MutableStateFlow<Movie>(
        Movie(
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
    )
    val movieState : StateFlow<Movie> = _movieState

    private val _isFavoriteState = MutableStateFlow<Boolean>(false)
    val isFavoriteState: StateFlow<Boolean> = _isFavoriteState

    fun getDetailMovie(movieId: Int){
        viewModelScope.launch {
            movieUseCase.getDetailMovie(movieId).collect{
                _movieState.value = it
            }
            getIsFavorite()
        }
    }

    fun setFavoriteMovie(movie: Movie, state: Boolean){
        movieUseCase.setFavoriteMovies(movie,state)
        getIsFavorite()
    }

    fun getIsFavorite(){
        _isFavoriteState.value = _movieState.value.isFavorite
    }
}