package com.example.doctors.ui.navigation

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.doctors.MainScreen
import com.example.doctors.Screen

@Composable
fun BottomNavigationDoctor(navController: NavController) {
    val backStackEntryState = navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryState.value?.destination
    val screens = listOf(MainScreen.Doctors, MainScreen.History, MainScreen.Profile)


    currentDestination?.route?.let {
        if (it != Screen.Registration.route &&
            it != Screen.SignIn.route
        ) {

            Log.i("Nav", "BottomNavigation $it")

            BottomNavigation {
                screens.forEach { currentScreen ->
                    val labelString = stringResource(id = currentScreen.labelResourcesId)

                    BottomNavigationItem(
                        selected = currentDestination.hierarchy.any { it.route == currentScreen.route },
                        onClick = {
                            navController.navigate(currentScreen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                                  },
                        icon = {
                            Icon(
                                painter = painterResource(id = currentScreen.iconResourcesId),
                                contentDescription = labelString
                            )
                        },
                        label = { Text(text = labelString) }
                    )
                }
            }

        }
    }


}