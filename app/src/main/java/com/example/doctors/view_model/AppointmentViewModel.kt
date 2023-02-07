package com.example.doctors.view_model

import androidx.compose.runtime.MutableState
import androidx.lifecycle.*
import com.example.doctors.datebase.DoctorsRecordRemoteDataSource
import com.example.doctors.entities.PlaceToWrite
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.util.*

class AppointmentViewModel() : ViewModel() {
    private lateinit var userId: String

    private val db = DoctorsRecordRemoteDataSource

    var places: LiveData<MutableList<PlaceToWrite>> = db.places

    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String>
        get() = _showMessage

    fun updateDateForPlaces(
        currentDate: MutableState<Calendar>,
        appointmentViewModel: AppointmentViewModel,
        doctorId: String
    ) {
        appointmentViewModel.disableListenerCollectionPlaces()
        appointmentViewModel.enableListenerCollection(currentDate.value, doctorId = doctorId)
    }

    fun enableListenerCollection(currentDate: Calendar, doctorId: String) {
        db.enableListenerCollectionPlacces(
            doctorId,
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DATE),
        )
    }

    fun disableListenerCollectionPlaces() = db.disableListenerCollectionPlaces()

    fun initVariable(userId: String, doctorId: String) {
        this.userId = userId
        //this.doctorId = doctorId
    }

    fun takeOfPlace(placeId: String, doctorId: String) = viewModelScope.launch {
        val task = db.deleteTakenPlace(placeId, doctorId)

        task.addOnCompleteListener {}
        task.addOnFailureListener {showMessage("Произошла ошибка. попробуйте снова")}
        task.addOnCanceledListener {showMessage("Произошла ошибка. попробуйте снова")}
    }

    fun takePlace(placeToWrite: PlaceToWrite, idPatient: String) = viewModelScope.launch {
        placeToWrite.isTaken = true
        placeToWrite.idPatient = idPatient

        val task = db.createTakenPlace(placeToWrite)

        task.addOnCompleteListener {}
        task.addOnFailureListener {showMessage("Произошла ошибка. попробуйте снова")}
        task.addOnCanceledListener {showMessage("Произошла ошибка. попробуйте снова")}
    }

    private fun showMessage(message: String) {
        _showMessage.value = message
    }

}