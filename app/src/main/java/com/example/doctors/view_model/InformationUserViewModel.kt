package com.example.doctors.view_model

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

    private val _userInfo = MutableLiveData<User?>(null)
    val userInfo: LiveData<User?>
        get() = _userInfo

    fun getUserInformation() = viewModelScope.launch{
        val userId = authDb.getUser()?.uid
        userId?.let {
            userDb.getUserInfo(userId = it)
                .addOnSuccessListener { docScnapshot ->
                    val user = docScnapshot.toObject(User::class.java)
                    _userInfo.value = user
                }
        }
    }

    fun getUserName() = authDb.getUser()?.displayName
}