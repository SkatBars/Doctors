package com.example.doctors.ui.views.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doctors.Screen
import com.example.doctors.ui.components.*
import com.example.doctors.util.emailIfValid
import com.example.doctors.view_model.AuthorizationViewModel

@Composable
fun SignInView(navController: NavController, scaffoldState: ScaffoldState) {
    val viewModel: AuthorizationViewModel = viewModel()

    BackgroundAuthorization(sizeBackgroundImage = 410.dp) {
        Column {
                TitleAuth(text = "Авторизация")

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            TextFieldEmail(email = email, onValueChange = { email = it })
            TextFieldPassword(password = password, onValueChange = { password = it })

            AppButton(dataIsValid = email.emailIfValid(), text = "Войти") {
                viewModel.signInWithEmail(
                    email = email,
                    password = password
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







