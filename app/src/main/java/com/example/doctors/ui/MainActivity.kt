package com.example.doctors.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.doctors.Screen
import com.example.doctors.ui.navigation.BottomNavigationDoctor
import com.example.doctors.ui.navigation.MainNavHost
import com.example.doctors.ui.theme.MaterialThemeDoctor
import com.example.doctors.view_model.AuthorizationViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val scaffoldState = rememberScaffoldState()

            val viewModel: AuthorizationViewModel = viewModel()
            var startDestination = Screen.SignIn.route

            if (viewModel.isAuthorization()) {
                startDestination = Screen.Main.route
            }


            val navController = rememberNavController()

            MaterialThemeDoctor() {
                Scaffold(
                    bottomBar = {
                        Log.i("Nav", "bottomBar")
                        BottomNavigationDoctor(
                            navController = navController
                        )
                    },
                    scaffoldState = scaffoldState
                    ) {
                    Log.i("Nav", "MainNavHost")
                    Box(Modifier.padding(it)) {
                        MainNavHost(
                            navController = navController,
                            startDestination = startDestination,
                            scaffoldState = scaffoldState
                        )
                    }
                }
            }
        }
    }
}