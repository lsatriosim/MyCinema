package com.example.mycinema.favorite

import com.example.mycinema.favorite.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module{
    viewModel { FavoriteViewModel(get()) }
}