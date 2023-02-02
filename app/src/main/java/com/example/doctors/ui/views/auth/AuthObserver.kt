package com.example.doctors.ui.views.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.doctors.Screen
import com.example.doctors.view_model.AuthorizationViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ObserverRequestsToFirebase(
    viewModel: AuthorizationViewModel,
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    val scope = rememberCoroutineScope()

    val result by viewModel.answerRequestSignIn.observeAsState(null)

    if (result?.isSuccess == true) {
        navController.navigate(Screen.Main.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

    }
    if (result?.isFailure == true) {
        scope.launch {
            scaffoldState.snackbarHostState
                .showSnackbar("Произошла ошибка, попробуйте снова")
        }

    }
}