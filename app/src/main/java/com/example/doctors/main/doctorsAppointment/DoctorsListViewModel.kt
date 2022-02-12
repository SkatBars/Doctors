package com.example.doctors.main.doctorsAppointment

import androidx.lifecycle.ViewModel
import com.example.doctors.authorization.data.FirebaseAuthDataSource
import com.example.doctors.main.doctorsAppointment.data.DoctorsRecordRemoteDataSource

class DoctorsListViewModel(
    private val myAuth : FirebaseAuthDataSource,
    private val db: DoctorsRecordRemoteDataSource
    ) : ViewModel() {
}