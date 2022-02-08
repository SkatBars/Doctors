package com.example.doctors.autorization.data.signIn

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.doctors.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    private val viewModel: SignInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }


}