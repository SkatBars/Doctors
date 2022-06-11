package com.example.doctors.view_model

import androidx.lifecycle.*
import com.example.doctors.datebase.FirebaseAuthDataSource
import com.example.doctors.entities.Doctor
import com.example.doctors.datebase.DoctorsRecordRemoteDataSource
import kotlinx.coroutines.launch

class DoctorsListViewModel(
    private val myAuth : FirebaseAuthDataSource = FirebaseAuthDataSource(),
    private val db: DoctorsRecordRemoteDataSource = DoctorsRecordRemoteDataSource()
    ) : ViewModel() {

    private val _openAppointmentFragment = MutableLiveData<String>()
    val openAppointmentFragment: LiveData<String>
        get() = _openAppointmentFragment

    private val _openSignInFragment = MutableLiveData<Boolean>(false)
    val openSignInFragment: LiveData<Boolean>
        get() = _openSignInFragment

    private val _doctors = db.doctors
    val doctors: LiveData<MutableList<Doctor>>
        get() = _doctors


    fun getUser() = myAuth.getUser()

    fun signOut() {
        viewModelScope.launch {
            myAuth.signOut()
            _openSignInFragment.value = true
        }
    }

    fun openAppointmentFragment(doctorId: String) {
        _openAppointmentFragment.value = doctorId
    }

    fun getOptionsForDoctorsList (
        lifecycleOwner: LifecycleOwner,
        value: String = "rating",
        reverse: Boolean = false
    )
    {
        /*val task = db.getQueryDoctors(value, reverse)
            task.addOnCompleteListener {
                _options.value =  FirestoreRecyclerOptions
                    .Builder<Doctor>()
                    .setQuery(task.result.query, Doctor::class.java)
                    .setLifecycleOwner(lifecycleOwner)
                    .build()
            }*/

    }


    fun enableListenerCollection( keySort: String = "rating", reverse: Boolean = false ) {
        db.enableListenerCollectionDoctor(keySort = keySort, reverse = reverse )
    }

    fun disableListenerCollectionPlaces() = db.disableListenerCollectionDoctors()
}