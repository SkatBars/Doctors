package com.example.doctors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.doctors.ui.ChooseDoctor
import com.example.doctors.ui.HistoryView
import com.example.doctors.ui.Profile
import com.example.doctors.ui.theme.MaterialThemeDoctor
import org.koin.core.scope.ScopeID

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MaterialThemeDoctor(true) {
                Scaffold(
                    bottomBar = { BottomNavigationDoctor(navController = navController)}
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ChooseDoctor.route
                    ) {
                        composable(Screen.ChooseDoctor.route) { ChooseDoctor()}
                        composable(Screen.History.route) { HistoryView()}
                        composable(Screen.Profile.route) { Profile() }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationDoctor(navController: NavController) {
    val backStackEntryState = navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryState.value?.destination
    val screens = listOf(Screen.ChooseDoctor, Screen.History, Screen.Profile)


    BottomNavigation {
        screens.forEach { currentScreen ->
            val labelString = stringResource(id = currentScreen.labelResourcesId)

            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any {it.route == currentScreen.route} == true,
                onClick = { navController.navigate(currentScreen.route) },
                icon = {Icon(
                    painter = painterResource(id = currentScreen.iconResourcesId),
                    contentDescription = labelString
                )},
                label = {Text(text = labelString)}
            )
        }
    }
}