package com.example.doctors.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val darkPallete = darkColors(
    primary = Blue500,
    primaryVariant = Blue700,
    background = Gray700
)

private val lightPallete = lightColors(
    primary = Blue500,
    primaryVariant = Blue700,
    background = Gray500
)

@Composable
fun DafultTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        darkPallete
    }else {
        lightPallete
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}