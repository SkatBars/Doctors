package com.example.doctors.view_model

import androidx.compose.runtime.MutableState
import androidx.lifecycle.*
import com.example.doctors.COUNT_PLACES_FOR_WRITE_OF_DAY
import com.example.doctors.datebase.DoctorsRecordRemoteDataSource
import com.example.doctors.entities.PlaceToWrite
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch
import java.util.*

class AppointmentViewModel() : ViewModel() {
    private lateinit var userId: String

    private val db = DoctorsRecordRemoteDataSource

    private val _places = MutableLiveData<MutableList<PlaceToWrite>>()
    val places: LiveData<MutableList<PlaceToWrite>>
        get() = _places

    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String>
        get() = _showMessage

    private lateinit var snapshotListenerPlaces: ListenerRegistration

    private fun updateListPlaces(
        value: QuerySnapshot?,
        doctor: String,
        year: Int,
        month: Int,
        day: Int
    ) {
        val tempList = mutableListOf<PlaceToWrite>()

        for (i in 0 until COUNT_PLACES_FOR_WRITE_OF_DAY) {
            tempList.add(
                PlaceToWrite(
                    doctor, "", i,
                    getTimeByNumber(i), year,
                    month, day, false
                )
            )
        }

        val currentList = value?.toObjects(PlaceToWrite::class.java)

        if (currentList != null) {
            for (place in currentList) {
                tempList[place.number] = place
            }
        }

        _places.value = tempList
    }

    private fun getTimeByNumber(number: Int): String {
        return if (number <= 3) {
            "${number + 9}:00-${number + 10}:00"
        } else {
            "${number + 10}:00-${number + 11}:00"
        }
    }

    fun updateDateForPlaces(
        currentDate: MutableState<Calendar>,
        appointmentViewModel: AppointmentViewModel,
        doctorId: String
    ) {
        appointmentViewModel.disableListenerCollectionPlaces()
        appointmentViewModel.enableListenerCollection(currentDate.value, doctorId = doctorId)
    }

    fun enableListenerCollection(currentDate: Calendar, doctorId: String) {
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val date = currentDate.get(Calendar.DATE)

        val query = db.getQueryPlaces(
            doctorId,
            year,
            month,
            date
        )

        snapshotListenerPlaces =
            query.addSnapshotListener { value, error ->
                updateListPlaces(value, doctorId, year, month, date)
            }
    }

    fun disableListenerCollectionPlaces() = snapshotListenerPlaces.remove()

    fun takeOfPlace(placeId: String, doctorId: String) = viewModelScope.launch {
        val task = db.deleteTakenPlace(placeId, doctorId)

        task.addOnCompleteListener {}
        task.addOnFailureListener { showMessage("Произошла ошибка. попробуйте снова") }
        task.addOnCanceledListener { showMessage("Произошла ошибка. попробуйте снова") }
    }

    fun takePlace(placeToWrite: PlaceToWrite, idPatient: String) = viewModelScope.launch {
        placeToWrite.isTaken = true
        placeToWrite.idPatient = idPatient

        val task = db.createTakenPlace(placeToWrite)

        task.addOnCompleteListener {}
        task.addOnFailureListener { showMessage("Произошла ошибка. попробуйте снова") }
        task.addOnCanceledListener { showMessage("Произошла ошибка. попробуйте снова") }
    }

    private fun showMessage(message: String) {
        _showMessage.value = message
    }

}