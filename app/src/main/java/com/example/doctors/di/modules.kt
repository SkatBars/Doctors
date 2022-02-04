package com.example.doctors.di

import com.example.doctors.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    viewModel { SplashViewModel() }
}