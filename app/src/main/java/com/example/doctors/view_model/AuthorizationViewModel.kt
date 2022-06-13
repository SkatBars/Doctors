package com.example.doctors.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctors.datebase.FirebaseAuthDataSource
import kotlinx.coroutines.launch

class AuthorizationViewModel(private val db: FirebaseAuthDataSource) : ViewModel() {

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

    fun signInWithEmail(email: String, password: String) {
        viewModelScope.launch {
            db.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                with(task) {
                    addOnCompleteListener { openMainFragment() }
                    addOnCanceledListener { showMessage("Операция была отменена, попробуйте снова") }
                    addOnFailureListener { showMessage("Произошла ошибка, попробуйте снова") }
                }
            }
        }
    }

    fun register(email: String, password: String, repeatPassword: String) {
        if (password == repeatPassword) {
            viewModelScope.launch {
                db.createUser(email, password).addOnCompleteListener { task ->
                    with(task) {
                        addOnCompleteListener { openMainFragment() }
                        addOnCanceledListener { showMessage("Операция была отменена, попробуйте снова") }
                        addOnFailureListener { showMessage("Произошла ошибка, попробуйте снова") }
                    }
                }
            }
        } else {
            showMessage("Пароли не совпадают")
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            val task = db.signInWithGoogle(idToken)
            with(task) {
                addOnCompleteListener { openMainFragment() }
                addOnCanceledListener { showMessage("Операция была отменена, попробуйте снова") }
                addOnFailureListener { showMessage("Произошла ошибка, попробуйте снова") }
            }

        }
    }

    private fun openMainFragment() {
        _openMainFragmentEvent.value = true
    }

    private fun showMessage(message: String) {
        _showSnackbar.value = message
    }

}