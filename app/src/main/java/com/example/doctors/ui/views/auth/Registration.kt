package com.example.doctors.ui.views.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doctors.ui.components.*
import com.example.doctors.util.emailIfValid
import com.example.doctors.view_model.AuthorizationViewModel

@Composable
fun Registration(navController: NavController, scaffoldState: ScaffoldState) {
    BackgroundAuthorization(sizeBackgroundImage = 350.dp) {
        Column {
            TitleAuth("Регистрация")

            val viewModel: AuthorizationViewModel = viewModel()

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var repeatPassword by remember { mutableStateOf("") }

            TextFieldEmail(email = email, onValueChange = { email = it })
            TextFieldPassword(password = password, onValueChange = { password = it })

            TextFieldsWithLabelError(
                value = repeatPassword,
                onValueChange = { text -> repeatPassword = text },
                labelText = "Повторите пароль",
                visualTransformation = PasswordVisualTransformation(),
                isError = password != repeatPassword,
                errorText = "Пароли не совпадают"
            )

            AppButton(
                isEnabled = (password == repeatPassword && email.emailIfValid()),
                text = "Зарегистрироваться"
            ) {
                viewModel.register(email = email, password = password)
            }

            ObserverRequestsToFirebase(
                viewModel = viewModel,
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
    }
}

