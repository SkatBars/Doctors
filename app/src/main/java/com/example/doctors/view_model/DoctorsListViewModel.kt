package com.example.doctors.view_model

import androidx.lifecycle.*
import com.example.doctors.datebase.FirebaseAuthDataSource
import com.example.doctors.entities.Doctor
import com.example.doctors.datebase.DoctorsRecordRemoteDataSource
import com.example.doctors.ui.views.doctors.chooseDoctor.KeyForSort
import kotlinx.coroutines.launch

class DoctorsListViewModel() : ViewModel() {

    private val myAuth = FirebaseAuthDataSource
    private val db = DoctorsRecordRemoteDataSource

    private val _doctors = db.doctors
    val doctors: LiveData<MutableList<Doctor>>
        get() = _doctors


    fun getUser() = myAuth.getUser()

    fun signOut() {
        viewModelScope.launch {
            myAuth.signOut()
        }
    }

    fun enableListenerCollection(keyForSort: KeyForSort) {
        db.enableListenerCollectionDoctor(keySort = keyForSort.property, reverse = keyForSort.isReverse)
    }

    fun disableListenerCollectionPlaces() = db.disableListenerCollectionDoctors()
}