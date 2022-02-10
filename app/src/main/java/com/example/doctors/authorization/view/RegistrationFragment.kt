package com.example.doctors.authorization.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.doctors.R
import com.example.doctors.authorization.viewModel.AuthorizationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.doctors.databinding.FragmentRegistrationBinding
import org.koin.android.ext.android.bind

class RegistrationFragment : Fragment() {

    private val viewModel: AuthorizationViewModel by viewModel()
    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater)
        configurationClickListeners()
        configurationLiveDataObservers()

        return binding.root
    }

    private fun configurationLiveDataObservers() {

    }

    private fun configurationClickListeners() {
        with(binding) {
            registrationBtn.setOnClickListener {
                val email = emailEditTextRegistration.text.toString()
                val password = passwordEditTextRegistration.text.toString()
                val repeatPassword = repeatPasswordEditTextRegistration.text.toString()

                viewModel.register(email, password, repeatPassword)}
            backBtn.setOnClickListener {findNavController().popBackStack()}
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}