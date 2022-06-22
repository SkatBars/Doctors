package com.example.doctors.ui

import android.os.Bundle
import androidx.compose.foundation.layout.*
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
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoctorItem(doctor: Doctor, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(start = 8.dp, end = 16.dp, top = 8.dp),

        shape = RoundedCornerShape(24.dp),
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
            Avatar(uriImage = doctor.urlAvatar)

            Column(
                modifier = Modifier.padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                DoctorName(doctorName = doctor.name)
                PriceText(price = doctor.avaragePrice)
                RatingText(rating = doctor.rating)
            }
        }

    }
}

@Composable
fun Avatar(uriImage: String) {
    GlideImage(
        imageModel = uriImage,

        modifier = Modifier
            .padding(start = 16.dp)
            .width(80.dp)
            .height(80.dp)
            .clip(shape = RoundedCornerShape(12.dp))
    )
}

@Composable
fun DoctorName(doctorName: String) {
    Text(
        text = doctorName,
        color = MaterialTheme.colors.primaryVariant,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    )
}

@Composable
fun PriceText(price: Int) {
    Text(
        text = "price: $price",
        fontSize = 16.sp
    )
}

@Composable
fun RatingText(rating: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "$rating",
            modifier = Modifier.padding(top = 8.dp),
            color = Color.Gray,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
        )
        Icon(
            painter = painterResource(
                id = R.drawable.ic_star),
                contentDescription = "rating",
                modifier = Modifier.padding(top = 8.dp, start = 4.dp),
                tint = Color.Gray
        )
    }

}
