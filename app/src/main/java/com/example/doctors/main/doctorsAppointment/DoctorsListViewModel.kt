package com.example.doctors.main.doctorsAppointment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors.authorization.data.FirebaseAuthDataSource
import com.example.doctors.main.doctorsAppointment.data.DoctorsRecordRemoteDataSource
import kotlinx.coroutines.launch

class DoctorsListViewModel(
    private val myAuth : FirebaseAuthDataSource,
    private val db: DoctorsRecordRemoteDataSource
    ) : ViewModel() {

    private val _openSignInFragment = MutableLiveData<Boolean>(false)
    val openSignInFragment: LiveData<Boolean>
        get() = _openSignInFragment

    fun signOut() {
        viewModelScope.launch {
            myAuth.signOut()
            _openSignInFragment.value = true
        }
    }
}