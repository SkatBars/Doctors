package com.example.doctors.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors.datebase.FirebaseAuthDataSource
import com.example.doctors.datebase.UserRemoteDataSource
import com.example.doctors.entities.Toothes
import com.example.doctors.entities.User
import kotlinx.coroutines.launch

class InformationUserViewModel : ViewModel() {
    val authDb = FirebaseAuthDataSource
    val userDb = UserRemoteDataSource

    private val _toothes = MutableLiveData<List<Toothes>>()
    val toothes: LiveData<List<Toothes>>
        get() = _toothes

    fun getToothes() = viewModelScope.launch{
        val userId = authDb.getUser()?.uid
        userId?.let {
            userDb.getToothes(userId = it)
                .addOnSuccessListener { docScnapshot ->
                    val dataFromDb = docScnapshot.toObject(User::class.java)

                    val tempList = mutableListOf<Toothes>()
                    dataFromDb?.toothes?.forEach { id ->
                        tempList.add(getToothById(id))
                    }
                    _toothes.value = tempList
                }
        }
    }

    fun getUserName() = authDb.getUser()?.displayName

    private fun getToothById(toothId: String): Toothes {
        return when(toothId) {
            Toothes.BrokenTooth.id -> Toothes.BrokenTooth
            Toothes.InWorkTooth.id -> Toothes.InWorkTooth
            Toothes.HealthyTooth.id -> Toothes.HealthyTooth
            else -> Toothes.СariesTooth
        }
    }
}