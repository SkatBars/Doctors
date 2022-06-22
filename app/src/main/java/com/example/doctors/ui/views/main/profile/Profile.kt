package com.example.doctors.ui

import android.widget.Toolbar
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doctors.entities.Toothes
import com.example.doctors.ui.views.main.profile.ChangeCurrentTooth
import com.example.doctors.ui.views.main.profile.Mouth
import com.example.doctors.view_model.InformationUserViewModel

@Composable
fun Profile() {
    val viewModelUser: InformationUserViewModel = viewModel()

    viewModelUser.getToothes()

    val toothes: List<Toothes> by viewModelUser.toothes.observeAsState(emptyList())
    val indexSelected = remember { mutableStateOf(5) }

    if (toothes.isNotEmpty()) {

        Column {
            TopAppBar {
                LogoText(PaddingValues(start = 8.dp))
            }

            Text(
                text = "Здесь вы можете ознакомиться с состоянием ваших зубов на данный момент",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Card(modifier = Modifier.padding(16.dp)) {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    ChangeCurrentTooth(toothes = toothes, indexSelected = indexSelected)
                    Mouth(toothes = toothes, indexSelected = indexSelected)
                }
            }
        }
    }
}

@Composable
fun LogoText(paddingValues: PaddingValues) {
    Text(
        text = "Анализ ротовой полости",
        color = Color.White,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
    )
}

