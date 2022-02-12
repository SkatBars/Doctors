package com.example.doctors.di

import com.example.doctors.authorization.data.FirebaseAuthDataSource
import com.example.doctors.authorization.viewModel.AuthorizationViewModel
import com.example.doctors.main.MainViewModel
import com.example.doctors.main.doctorsAppointment.data.DoctorsRecordRemoteDataSource
import com.example.doctors.splash.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    single { FirebaseAuth.getInstance() }
    single { Firebase.firestore }

    single { FirebaseAuthDataSource(auth = get()) }
    single {DoctorsRecordRemoteDataSource(firestore = get())}

    viewModel { SplashViewModel() }
    viewModel { AuthorizationViewModel(db = get()) }
    viewModel { MainViewModel(db = get()) }
}