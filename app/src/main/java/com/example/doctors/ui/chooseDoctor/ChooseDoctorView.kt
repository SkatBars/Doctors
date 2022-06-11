package com.example.doctors.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doctors.KeyForSort
import com.example.doctors.R
import com.example.doctors.view_model.DoctorsListViewModel
import com.example.doctors.entities.Doctor



@Composable
fun ChooseDoctor() {
    Column(Modifier.padding(top = 32.dp, start = 8.dp)) {
        Title()
        val viewModel: DoctorsListViewModel = viewModel()

        if (viewModel.doctors.value == null) {
            viewModel.enableListenerCollection(KeyForSort.RatingDescending)
        }

        val textState = remember { mutableStateOf("") }
        SearchDoctors(textState = textState)

        val keysForSort = listOf(
            KeyForSort.RatingAscending,
            KeyForSort.RatingDescending,
            KeyForSort.PriceAscending,
            KeyForSort.PriceDescending
        )

        MySpinner(
            items = keysForSort,
            hint = "Отсортировать список врачей",
            tint = MaterialTheme.colors.primaryVariant,
            padding = PaddingValues(top = 32.dp, start = 8.dp, end = 32.dp),
            onClick = {
                viewModel.disableListenerCollectionPlaces()
                viewModel.enableListenerCollection(it)
            }
        )

        val doctors: List<Doctor> by viewModel.doctors.observeAsState(listOf())
        ListDoctors(doctors = doctors, filterSample = textState.value)
    }
}

@Composable
fun Title() {
    Text(
        text = "Hello, Sema"
    )
    Text(
        text = "Find you doctor",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ListDoctors(doctors : List<Doctor>, filterSample: String) {
    LazyColumn {
        items(doctors.filter { it ->
            it.name.contains(other = filterSample, ignoreCase = true)
        }, key = {it.id}) { doctor ->
            DoctorItem(doctor = doctor)
        }
    }
}

@Composable
fun SearchDoctors(textState: MutableState<String>) {
    OutlinedTextField(
        value = textState.value,
        onValueChange ={it -> textState.value = it},
        label = {Text("Введите фамилию доктора")},
        trailingIcon = {Icon(
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


