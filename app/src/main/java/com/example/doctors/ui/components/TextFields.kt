package com.example.doctors.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.doctors.util.emailIfValid

@Composable
fun TextFieldsWithLabelError(
    value: String,
    onValueChange: (newValue: String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    errorText: String = "",
    labelText: String = "",
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    OutlinedTextField(
        value = value,
        onValueChange = { text -> onValueChange(text) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        label = { Text(labelText) },
        visualTransformation = visualTransformation,
        isError = isError
    )

    if (isError) {
        Text(
            text = errorText,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun TextFieldEmail(email: String, onValueChange: (newValue: String) -> Unit) {
    TextFieldsWithLabelError(
        value = email,
        onValueChange = { text ->  onValueChange(text) },
        labelText = "Введите email",
        isError = email.emailIfValid().not(),
        errorText = "Email не валиден",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun TextFieldPassword(password: String, onValueChange: (newValue: String) -> Unit) {
    TextFieldsWithLabelError(
        value = password,
        onValueChange = { text -> onValueChange(text) },
        labelText =  "Введите пароль",
        visualTransformation = PasswordVisualTransformation(),
    )
}