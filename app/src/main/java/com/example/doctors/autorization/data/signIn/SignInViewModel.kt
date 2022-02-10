package com.example.doctors.autorization.data.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors.autorization.data.FirebaseAuthDataSource
import kotlinx.coroutines.launch

class SignInViewModel(private val db: FirebaseAuthDataSource) : ViewModel() {

    private val _openMainFragmentEvent = MutableLiveData<Boolean>(false)
    val openMainFragmentEvent: LiveData<Boolean>
        get() = _openMainFragmentEvent

    private val _showSnackbar = MutableLiveData<String>()
    val showSnackbar: LiveData<String>
        get() = _showSnackbar

    val gsoDb = db.gso

    init {
        autoAuthorization()
    }

    private fun autoAuthorization() {
        val user = db.getUser()
        if (user != null) {
            _openMainFragmentEvent.value = true
        }
    }

    fun logInWithEmail(email: String, password: String) {
        viewModelScope.launch {
                db.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isCanceled) {
                        _showSnackbar.value = "Ошибка загрузки"
                    }

                    if (task.isSuccessful) {
                        _openMainFragmentEvent.value = true
                    }
                }
        }
    }

    fun logInWithGoogle() {

    }

}