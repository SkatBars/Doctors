package com.example.doctors.ui.views.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.doctors.R
import com.example.doctors.Screen
import com.example.doctors.view_model.AuthorizationViewModel
import kotlinx.coroutines.launch

@Composable
fun SignInView(navController: NavController, scaffoldState: ScaffoldState) {
    val viewModel: AuthorizationViewModel = viewModel()

    Column(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
        Image(
            painter = painterResource(id = R.drawable.ic_doctor),
            contentDescription = "icon_doctor"
        )

        BackgroundRoundCard(
            color = Color.White,
            radius = 16.dp
        ) {
            Column {
                TitleAuth(text = "Авторизация")

                val email = remember { mutableStateOf("") }
                val password = remember { mutableStateOf("") }

                TextFieldEmailAndPassword(email, password, PaddingValues(8.dp))

                ButtonSignIn(click = {
                    viewModel.signInWithEmail(
                        email = email.value,
                        password = password.value
                    )
                })

                ButtonShowRegistrationView { navController.navigate(Screen.Registration.route) }
                ObserverRequestsToFirebase(
                    viewModel = viewModel,
                    navController = navController,
                    scaffoldState = scaffoldState
                )
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun ObserverRequestsToFirebase(
    viewModel: AuthorizationViewModel,
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    val scope = rememberCoroutineScope()
    val observer: State<Result<String>?> = viewModel.answerRequestSignIn.observeAsState(null)


    if (observer.value?.isSuccess == true) {
        navController.navigate(Screen.Main.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

    }
    if (observer.value?.isFailure == true) {
        scope.launch {
            scaffoldState.snackbarHostState
                .showSnackbar("Произошла ошибка, попробуйте снова")
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







