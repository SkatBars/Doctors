package com.example.doctors.ui.views.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doctors.view_model.AuthorizationViewModel

@Composable
fun Registration(navController: NavController, scaffoldState: ScaffoldState) {
    BackgroundAuthorization(sizeBackgroundImage = 350.dp) {
        Column {
            TitleAuth("Регистрация")

            val viewModel: AuthorizationViewModel = viewModel()

            val email = remember { mutableStateOf("") }
            val password = remember { mutableStateOf("") }

            val emailIsValid = remember { mutableStateOf(true) }
            val passwordIsValid = remember { mutableStateOf(true) }

            TextFieldEmailAndPassword(
                email = email,
                password = password,
                PaddingValues(8.dp),
                emailIsValid = emailIsValid,
            )

            RepeatPasswordTextField(password = password, passwordIsValid = passwordIsValid)

            AuthorizationButton(
                dataIsValid = (passwordIsValid.value && emailIsValid.value),
                text = "Зарегистрироваться"
            ) {
                viewModel.register(email = email.value, password = password.value)
            }

            ObserverRequestsToFirebase(
                viewModel = viewModel,
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
    }
}

@Composable
fun RepeatPasswordTextField(
    password: MutableState<String>,
    passwordIsValid: MutableState<Boolean>
) {
    val repeatPassword = remember { mutableStateOf("") }

    passwordIsValid.value = repeatPassword.value == password.value

    OutlinedTextField(
        value = repeatPassword.value,
        onValueChange = { text -> repeatPassword.value = text },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = { Text("Повторите пароль") },
        visualTransformation = PasswordVisualTransformation(),
        isError = passwordIsValid.value.not()
    )

    if (passwordIsValid.value.not()) {
        Text(
            "Пароли не совпадают",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}