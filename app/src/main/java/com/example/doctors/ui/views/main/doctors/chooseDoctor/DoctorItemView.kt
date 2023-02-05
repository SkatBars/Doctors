package com.example.doctors.ui

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.doctors.DoctorsScreen
import com.example.doctors.R
import com.example.doctors.entities.Doctor
import com.example.doctors.ui.components.CircleImage
import com.example.doctors.ui.components.RatingText
import com.example.doctors.ui.components.TextWithCaption
import com.example.doctors.ui.components.Title
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoctorItem(doctor: Doctor, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        onClick = {
            navController.currentBackStackEntry?.savedStateHandle?.set(
                "current_doctor",
                doctor
            )
            navController.navigate(DoctorsScreen.PlaceToWrite.route)
        }

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CircleImage(uriImage = doctor.urlAvatar)

            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Title(text = doctor.name, fontSize = 20.sp)

                TextWithCaption(
                    caption = "price",
                    text = doctor.avaragePrice.toString(),
                    fontSize = 16.sp
                )

                RatingText(rating = doctor.rating)
            }
        }

    }
}