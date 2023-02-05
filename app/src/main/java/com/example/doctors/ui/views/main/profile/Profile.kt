package com.example.doctors.ui

import android.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doctors.entities.Toothes
import com.example.doctors.ui.components.TextWithCaption
import com.example.doctors.ui.theme.*
import com.example.doctors.ui.views.main.profile.ChangeCurrentTooth
import com.example.doctors.ui.views.main.profile.Mouth
import com.example.doctors.util.parseListIdToListToothes
import com.example.doctors.view_model.InformationUserViewModel

@Composable
private fun TitleUser(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.h4,
        color = Blue500,
        fontWeight = FontWeight.Bold
    )

    Spacer(
        modifier = Modifier
            .width(200.dp)
            .height(3.dp)
            .background(Blue200)
    )
}

@Composable
fun Profile() {
    val viewModelUser: InformationUserViewModel = viewModel()
    val userInfo by viewModelUser.userInfo.observeAsState()
    val indexSelected = remember { mutableStateOf(5) }

    LaunchedEffect(key1 = Unit, block = {
        viewModelUser.getUserInformation()
    })

    userInfo?.let { info ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TitleUser(info.name)

            TextWithCaption(caption = "email:", text = info.email)
            TextWithCaption(caption = "Номер телефона:", text = info.phoneNumber)
            TextWithCaption(caption = "Информация:", text = info.information)

            val toothes = parseListIdToListToothes(info.toothes)
            Column(
                verticalArrangement = Arrangement.Bottom
            ) {
                Mouth(toothes = toothes, indexSelected = indexSelected)
                ChangeCurrentTooth(toothes = toothes, indexSelected = indexSelected)
            }
        }
    }
}


