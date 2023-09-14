package com.example.mycinema

import com.example.mycinema.detail.DetailViewModel
import com.example.mycinema.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}