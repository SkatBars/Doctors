package com.example.doctors.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doctors.R
import com.example.doctors.Screen
import com.example.doctors.view_model.AuthorizationViewModel

@Composable
fun AppButton(
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        enabled = isEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = text, fontSize = 24.sp)
    }
}

@Composable
fun AppTextButton(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text(text, fontSize = 18.sp)
    }
}

@Composable
fun ButtonSignOut(navController: NavController) {
    val authViewModel: AuthorizationViewModel = viewModel()

    TextButton(onClick = {
        authViewModel.signOut()
        navController.navigate(Screen.SignIn.route) {
            launchSingleTop = true
        }
    }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_exit),
            contentDescription = "sign out",
            modifier = Modifier
                .height(30.dp)
                .width(30.dp)
        )
    }
}