package com.example.doctors.main.doctors_list.appointment

import androidx.lifecycle.*
import com.example.doctors.main.doctors_list.data.DoctorsRecordRemoteDataSource
import com.example.doctors.main.doctors_list.data.PlaceToWrite
import kotlinx.coroutines.launch
import java.util.*

class AppointmentViewModel(private val db: DoctorsRecordRemoteDataSource) : ViewModel() {
    var places: LiveData<MutableList<PlaceToWrite>> = db.places

    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String>
        get() = _showMessage

    fun enableListenerCollection(doctor: String, currentDate: Date) {
        db.enableListenerCollection(doctor, currentDate)
    }

    fun disableListenerCollectionPlaces() = db.disableListenerCollectionPlaces()

    fun updatePlace(placeToWrite: PlaceToWrite) = viewModelScope.launch {
        val task = db.updatePlace(placeToWrite)

        task.addOnCompleteListener {}
        task.addOnFailureListener {showMessage("Произошла ошибка. попробуйте снова")}
        task.addOnCanceledListener {showMessage("Произошла ошибка. попробуйте снова")}
    }

    private fun showMessage(message: String) {
        _showMessage.value = message
    }

}