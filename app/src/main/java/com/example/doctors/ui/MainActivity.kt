package com.example.doctors.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.compose.rememberNavController
import com.example.doctors.Screen
import com.example.doctors.ui.navigation.BottomNavigationDoctor
import com.example.doctors.ui.navigation.MainNavHost
import com.example.doctors.ui.theme.MaterialThemeDoctor

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MaterialThemeDoctor() {
                Scaffold(
                    bottomBar = { BottomNavigationDoctor(navController = navController)}
                ) {
                    MainNavHost(navController = navController, startDestination = Screen.Doctors.route)
                }
            }
        }
    }
}