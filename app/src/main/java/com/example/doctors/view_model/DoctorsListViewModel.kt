package com.example.doctors.view_model

import androidx.lifecycle.*
import com.example.doctors.datebase.FirebaseAuthDataSource
import com.example.doctors.entities.Doctor
import com.example.doctors.datebase.DoctorsRecordRemoteDataSource
import com.example.doctors.entities.User
import com.example.doctors.ui.components.spiner.KeyForSort
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DoctorsListViewModel() : ViewModel() {

    private val myAuth = FirebaseAuthDataSource
    private val db = DoctorsRecordRemoteDataSource

    private val _doctors = db.doctors
    val doctors: LiveData<MutableList<Doctor>>
        get() = _doctors

    private val _updateIsLoaded = MutableLiveData(false)
    val updateIsLoaded: LiveData<Boolean>
        get() = _updateIsLoaded

    fun getUser() = myAuth.getUser()

    fun enableListenerCollection(keyForSort: KeyForSort) {
        db.enableListenerCollectionDoctor(
            keySort = keyForSort.property,
            reverse = keyForSort.isReverse
        )
    }

    fun updateRating(doctorId: String, rating: Double, isFirstUpdate: Boolean = false) =
        viewModelScope.launch {
            val doctor = async {
                return@async db.getDoctorById(doctorId)
            }.await().result.toObject(Doctor::class.java)

            doctor?.let {
                val newRating = rating + doctor.rating
                var newCount = doctor.countPeopleForRating
                if (isFirstUpdate) newCount++

                db.updateRating(doctorId, newRating, newCount)
                    .addOnSuccessListener {
                        _updateIsLoaded.value = true

                    }
            }
        }

    fun disableListenerCollectionPlaces() = db.disableListenerCollectionDoctors()
}