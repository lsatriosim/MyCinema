package com.example.mycinema.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(movieUseCase: MovieUseCase): ViewModel() {
    private val _movieState = MutableStateFlow<List<Movie>>(emptyList())
    val movieState : StateFlow<List<Movie>> = _movieState

    init{
        viewModelScope.launch {
            movieUseCase.getFavoriteMovies().collect{
                _movieState.value = it
            }
        }
    }
}