package com.example.doctors.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.doctors.MainScreen
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
                    Column(modifier = Modifier.padding(it)) {

                        MainNavHost(navController = navController, startDestination = MainScreen.Doctors.route)
                    }
                }
            }
        }
    }
}