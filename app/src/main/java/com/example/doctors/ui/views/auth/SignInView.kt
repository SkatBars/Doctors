package com.example.doctors.ui.views.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.doctors.Screen
import com.example.doctors.view_model.AuthorizationViewModel
import kotlinx.coroutines.launch

@Composable
fun SignInView(navController: NavController, scaffoldState: ScaffoldState) {
    val viewModel: AuthorizationViewModel = viewModel()

    BackgroundAuthorization(sizeBackgroundImage = 410.dp) {
        Column {
            TitleAuth(text = "Авторизация")

            val email = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }

            val emailIsValid = remember { mutableStateOf(true) }

            TextFieldEmailAndPassword(
                email,
                password,
                PaddingValues(8.dp),
                emailIsValid,
            )

            AuthorizationButton(dataIsValid = emailIsValid.value, text = "Войти") {
                viewModel.signInWithEmail(
                    email = email.value,
                    password = password.value
                )
            }

            ButtonShowRegistrationView { navController.navigate(Screen.Registration.route) }


            ObserverRequestsToFirebase(
                viewModel = viewModel,
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
    }
}

@Composable
private fun ButtonShowRegistrationView(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text("Зарегистрироваться", fontSize = 18.sp)
    }
}

@Composable
private fun ButtonSignIn(click: () -> Unit) {
    Button(
        onClick = { click() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text("Войти", fontSize = 24.sp)
    }
}







