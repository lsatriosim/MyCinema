package com.example.mycinema.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(movieUseCase: MovieUseCase): ViewModel() {
    private val _movieState = MutableStateFlow<com.example.core.data.Resource<List<Movie>>>(com.example.core.data.Resource.Loading())
    val movieState : StateFlow<com.example.core.data.Resource<List<Movie>>> = _movieState

    init{
        viewModelScope.launch {
            movieUseCase.getAllMovies().collect{
                _movieState.value = it
            }
        }
    }
}