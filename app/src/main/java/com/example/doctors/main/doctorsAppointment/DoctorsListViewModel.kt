package com.example.doctors.main.doctorsAppointment

import android.util.Log
import androidx.lifecycle.*
import com.example.doctors.authorization.data.FirebaseAuthDataSource
import com.example.doctors.main.doctorsAppointment.data.Doctor
import com.example.doctors.main.doctorsAppointment.data.DoctorsRecordRemoteDataSource
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DoctorsListViewModel(
    private val myAuth : FirebaseAuthDataSource,
    private val db: DoctorsRecordRemoteDataSource
    ) : ViewModel() {

    private val _openSignInFragment = MutableLiveData<Boolean>(false)
    val openSignInFragment: LiveData<Boolean>
        get() = _openSignInFragment

    private val _options = MutableLiveData<FirestoreRecyclerOptions<Doctor>?>(null)
    val options: LiveData<FirestoreRecyclerOptions<Doctor>?>
        get() = _options


    fun signOut() {
        viewModelScope.launch {
            myAuth.signOut()
            _openSignInFragment.value = true
        }
    }


    fun getOptionsForDoctorsList(lifecycleOwner: LifecycleOwner) = viewModelScope.launch {
        val task = db.getQueryDoctors()
            task.addOnCompleteListener {
                _options.value =  FirestoreRecyclerOptions
                    .Builder<Doctor>()
                    .setQuery(task.result.query, Doctor::class.java)
                    .setLifecycleOwner(lifecycleOwner)
                    .build()
            }
    }

}