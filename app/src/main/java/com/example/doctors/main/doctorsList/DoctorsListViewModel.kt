package com.example.doctors.main.doctorsList

import androidx.lifecycle.*
import com.example.doctors.authorization.data.FirebaseAuthDataSource
import com.example.doctors.main.doctorsList.data.Doctor
import com.example.doctors.main.doctorsList.data.DoctorsRecordRemoteDataSource
import com.firebase.ui.firestore.FirestoreRecyclerOptions
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


    fun getOptionsForDoctorsList(
        lifecycleOwner: LifecycleOwner,
        value: String = "rating",
        reverse: Boolean = false
    ) = viewModelScope.launch {
        val task = db.getQueryDoctors(value, reverse)
            task.addOnCompleteListener {
                _options.value =  FirestoreRecyclerOptions
                    .Builder<Doctor>()
                    .setQuery(task.result.query, Doctor::class.java)
                    .setLifecycleOwner(lifecycleOwner)
                    .build()
            }
    }

}