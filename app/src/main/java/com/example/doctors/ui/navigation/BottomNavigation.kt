package com.example.doctors.ui.navigation

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
import com.example.doctors.Screen

@Composable
fun BottomNavigationDoctor(navController: NavController) {
    val backStackEntryState = navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryState.value?.destination
    val screens = listOf(Screen.Doctors, Screen.History, Screen.Profile)


    BottomNavigation {
        screens.forEach { currentScreen ->
            val labelString = stringResource(id = currentScreen.labelResourcesId)

            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any {it.route == currentScreen.route} == true,
                onClick = { navController.navigate(currentScreen.route) },
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