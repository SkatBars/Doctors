package com.example.doctors.ui.views.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.doctors.R
import com.example.doctors.Screen
import com.example.doctors.view_model.AuthorizationViewModel
import kotlinx.coroutines.launch

@Composable
fun BackgroundAuthorization(
    sizeBackgroundImage: Dp,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
        Image(
            painter = painterResource(id = R.drawable.ic_doctor),
            contentDescription = "icon_doctor",
            modifier = Modifier
                .height(sizeBackgroundImage)
                .fillMaxWidth(  )
        )

        BackgroundRoundCard(
            color = Color.White,
            radius = 16.dp
        ) { content() }
    }
}

@Composable
fun BackgroundRoundCard(
    color: Color,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    radius: Dp,
    content: @Composable () -> Unit
) {
    Card(
        backgroundColor = color,
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxHeight()
            .fillMaxWidth(),
        shape = RoundedCornerShape(radius),
        content = content
    )
}

@Composable
fun LogoText(paddingValues: PaddingValues) {
    Text(
        text = "Skatik",
        color = Color.White,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    )
}

@Composable
fun TitleAuth(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.primaryVariant,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
}

private fun emailIfValid(email: String): Boolean {
    return (email.isEmpty() || email.matches(Regex("\\S*@\\S*[.]\\S*")))
}

@Composable
fun TextFieldEmailAndPassword(
    email: MutableState<String>,
    password: MutableState<String>,
    paddingValues: PaddingValues,
    emailIsValid: MutableState<Boolean>,
) {
    emailIsValid.value = emailIfValid(email = email.value)
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

@Composable
fun AuthorizationButton(
    text: String,
    dataIsValid: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        enabled = dataIsValid,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = text, fontSize = 24.sp)
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