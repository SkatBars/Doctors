package com.example.doctors.ui.views.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackgroundRoundCard(
    color: Color,
    paddingValues: PaddingValues = PaddingValues(),
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
    return (email.isEmpty() || email.matches(Regex("\\S*@\\S*[.]\\S*"))).not()
}

@Composable
fun TextFieldEmailAndPassword(
    email: MutableState<String>,
    password: MutableState<String>,
    paddingValues: PaddingValues
) {
    Column {

        OutlinedTextField(
            value = email.value,
            onValueChange = { text -> email.value = text },
            label = { Text("Введите email") },
            isError = emailIfValid(email = email.value),
            modifier = Modifier.fillMaxWidth().padding(paddingValues),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = password.value,
            onValueChange = { text -> password.value = text },
            label = { Text("Введите пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(paddingValues)
        )

    }
}
