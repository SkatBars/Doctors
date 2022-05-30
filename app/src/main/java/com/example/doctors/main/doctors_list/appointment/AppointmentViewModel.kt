package com.example.doctors.main.doctors_list.appointment

import androidx.lifecycle.*
import com.example.doctors.main.doctors_list.data.DoctorsRecordRemoteDataSource
import com.example.doctors.main.doctors_list.data.PlaceToWrite
import kotlinx.coroutines.launch
import java.util.*

class AppointmentViewModel(
    private val db: DoctorsRecordRemoteDataSource) : ViewModel() {
    private lateinit var userId: String

    var places: LiveData<MutableList<PlaceToWrite>> = db.places
    lateinit var doctorId: String

    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String>
        get() = _showMessage

    fun enableListenerCollection(year: Int, month: Int, day: Int) {
        db.enableListenerCollection(doctorId, year, month, day)
    }

    fun disableListenerCollectionPlaces() = db.disableListenerCollectionPlaces()

    fun initVariable(userId: String, doctorId: String) {
        this.userId = userId
        this.doctorId = doctorId
    }

    fun takePlace(placeToWrite: PlaceToWrite) = viewModelScope.launch {
        placeToWrite.isTaken = true
        placeToWrite.idPatient = userId

        val task = db.createTakenPlace(placeToWrite)

        task.addOnCompleteListener {}
        task.addOnFailureListener {showMessage("Произошла ошибка. попробуйте снова")}
        task.addOnCanceledListener {showMessage("Произошла ошибка. попробуйте снова")}
    }

    private fun showMessage(message: String) {
        _showMessage.value = message
    }

}