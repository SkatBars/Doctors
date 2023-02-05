package com.example.doctors.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.doctors.R
import com.example.doctors.util.emailIfValid

@Composable
fun SearchText(textState: MutableState<String>, labelText: String) {
    OutlinedTextField(
        value = textState.value,
        onValueChange = { it -> textState.value = it },
        label = { Text(labelText) },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "search"
            )
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledTrailingIconColor = MaterialTheme.colors.primaryVariant
        )
    )
}

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
        onValueChange = { text -> onValueChange(text) },
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
        labelText = "Введите пароль",
        visualTransformation = PasswordVisualTransformation(),
    )
}