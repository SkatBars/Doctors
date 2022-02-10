package com.example.doctors.di

import com.example.doctors.autorization.data.FirebaseAuthDataSource
import com.example.doctors.autorization.signIn.SignInViewModel
import com.example.doctors.splash.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    single { FirebaseAuth.getInstance() }
    single { FirebaseAuthDataSource(auth = get()) }

    viewModel { SplashViewModel() }
    viewModel { SignInViewModel(db = get()) }
}