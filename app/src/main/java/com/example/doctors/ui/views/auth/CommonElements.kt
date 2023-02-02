package com.example.doctors.ui.views.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.doctors.Screen
import com.example.doctors.util.emailIfValid
import com.example.doctors.view_model.AuthorizationViewModel
import kotlinx.coroutines.launch



@Composable
fun TextFieldEmailAndPassword(
    email: MutableState<String>,
    password: MutableState<String>,
    paddingValues: PaddingValues,
    emailIsValid: MutableState<Boolean>,
) {
    emailIsValid.value = email.value.emailIfValid()
    Column {
        OutlinedTextField(
            value = email.value,
            onValueChange = { text -> email.value = text },
            label = { Text("Введите email") },
            isError = emailIsValid.value.not(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { text -> password.value = text },
            label = { Text("Введите пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        )

    }
}

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