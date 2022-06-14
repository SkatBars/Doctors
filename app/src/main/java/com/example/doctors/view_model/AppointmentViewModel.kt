package com.example.doctors.view_model

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

    fun takePlace(placeToWrite: PlaceToWrite) = viewModelScope.launch {
        placeToWrite.isTaken = true
        placeToWrite.idPatient = "com.google.firebase.auth.internal.zzx@116113010"

        val task = db.createTakenPlace(placeToWrite)

        task.addOnCompleteListener {}
        task.addOnFailureListener {showMessage("Произошла ошибка. попробуйте снова")}
        task.addOnCanceledListener {showMessage("Произошла ошибка. попробуйте снова")}
    }

    private fun showMessage(message: String) {
        _showMessage.value = message
    }

}