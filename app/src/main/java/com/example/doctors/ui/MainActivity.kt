package com.example.doctors.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.doctors.MainScreen
import com.example.doctors.Screen
import com.example.doctors.ui.navigation.BottomNavigationDoctor
import com.example.doctors.ui.navigation.MainNavHost
import com.example.doctors.ui.theme.MaterialThemeDoctor
import com.example.doctors.view_model.AuthorizationViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: AuthorizationViewModel = viewModel()
            val startDestination =
                if (viewModel.isAuthorization()) Screen.Main.route else Screen.SignIn.route


            val navController = rememberNavController()

            MaterialThemeDoctor() {
                Scaffold(bottomBar = {
                        BottomNavigationDoctor(
                            navController = navController
                        )
                }) {
                    MainNavHost(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}