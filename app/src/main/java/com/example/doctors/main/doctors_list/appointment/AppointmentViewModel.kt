package com.example.doctors.main.doctors_list.appointment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.doctors.main.doctors_list.data.DoctorsRecordRemoteDataSource
import com.example.doctors.main.doctors_list.data.PlaceToWrite

class AppointmentViewModel(private val db: DoctorsRecordRemoteDataSource) : ViewModel() {
    var places: LiveData<List<PlaceToWrite>> = db.places
}