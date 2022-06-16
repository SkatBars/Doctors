package com.example.doctors.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.doctors.DoctorsScreen
import com.example.doctors.MainScreen
import com.example.doctors.Screen
import com.example.doctors.entities.Doctor
import com.example.doctors.ui.ChooseDoctor
import com.example.doctors.ui.HistoryView
import com.example.doctors.ui.views.main.doctors.placeToWrite.PlaceToWriteView
import com.example.doctors.ui.Profile
import com.example.doctors.ui.views.auth.SignInView
import com.example.doctors.ui.views.auth.Registration

@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.SignIn.route) { SignInView(navController) }
        composable(Screen.Registration.route) { Registration() }
        main(navController)
    }
}

private fun NavGraphBuilder.doctors(navController: NavController) {
    navigation(route = MainScreen.Doctors.route, startDestination = DoctorsScreen.ChooseDoctor.route) {
        composable(DoctorsScreen.ChooseDoctor.route) { ChooseDoctor(navController) }
        composable(DoctorsScreen.PlaceToWrite.route) {
            val currentDoctor = navController.previousBackStackEntry
                ?.arguments?.getParcelable<Doctor>("current_doctor")

            currentDoctor?.let { it1 -> PlaceToWriteView(it1, navController) }
        }
    }
}

private fun NavGraphBuilder.main(navController: NavController) {
    navigation(route = Screen.Main.route, startDestination = MainScreen.Doctors.route) {
        doctors(navController)
        composable(MainScreen.History.route) { HistoryView() }
        composable(MainScreen.Profile.route) { Profile() }
    }
}