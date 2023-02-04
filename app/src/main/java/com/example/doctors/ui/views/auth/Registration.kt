package com.example.doctors.ui.views.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doctors.entities.Toothes
import com.example.doctors.entities.User
import com.example.doctors.ui.components.*
import com.example.doctors.util.emailIfValid
import com.example.doctors.view_model.AuthorizationViewModel

@Composable
fun Registration(navController: NavController, scaffoldState: ScaffoldState) {
    BackgroundAuthorization(sizeBackgroundImage = 0.dp) {
        Column {
            TitleAuth("Регистрация")

            val viewModel: AuthorizationViewModel = viewModel()

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var repeatPassword by remember { mutableStateOf("") }
            var name by remember { mutableStateOf("") }
            var information by remember { mutableStateOf("") }
            var phoneNumber by remember { mutableStateOf("") }

            TextFieldEmail(email = email, onValueChange = { email = it })

            TextFieldsWithLabelError(
                value = name,
                onValueChange = { name = it },
                labelText = "Введите имя и фамилию",
                errorText = "Должно быть 2 слова",
                isError = name.split(" ").size != 2 && name.isNotEmpty()
            )

            TextFieldPassword(password = password, onValueChange = { password = it })

            TextFieldsWithLabelError(
                value = repeatPassword,
                onValueChange = { text -> repeatPassword = text },
                labelText = "Повторите пароль",
                visualTransformation = PasswordVisualTransformation(),
                isError = password != repeatPassword,
                errorText = "Пароли не совпадают"
            )

            TextFieldsWithLabelError(
                value = phoneNumber,
                labelText = "Введите номер телефона",
                isError = phoneNumber.isNotEmpty() && (phoneNumber.length != 10
                        || phoneNumber[0] == '8'),
                errorText = "Номер телефона имеет 10 цифр(8.....)",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { phoneNumber = it },
            )

            TextFieldsWithLabelError(
                value = information,
                labelText = "Введите информацию для врача",
                onValueChange = { information = it },
            )

            AppButton(
                isEnabled = (password == repeatPassword && email.emailIfValid() && name.split(" ").size == 2),
                text = "Зарегистрироваться"
            ) {
                val toothes = mutableListOf<String>()
                repeat(32) { toothes.add(Toothes.HealthyTooth.id) }

                val user = User(
                    name = name,
                    email = email,
                    phoneNumber = phoneNumber,
                    information = information,
                    toothes = toothes.toList()
                )

                viewModel.register(user = user, password = password)
            }

            ObserverRequestsToFirebase(
                viewModel = viewModel,
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
    }
}

