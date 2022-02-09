package com.example.doctors.autorization.data.signIn

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.doctors.R
import com.example.doctors.RC_SIGN_IN
import com.example.doctors.databinding.FragmentSignInBinding
import com.example.doctors.databinding.FragmentSplashBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    private val viewModel: SignInViewModel by viewModel()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater)

        binding.signInGoogleBtn.setOnClickListener {
            showGoogleAuthorizationActivity()
        }

        return binding.root
    }

    private fun showGoogleAuthorizationActivity() {
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), viewModel.gsoDb)
        val signInGoogleIntent = googleSignInClient.signInIntent
        requireActivity().startActivityIfNeeded(signInGoogleIntent, RC_SIGN_IN)
    }

}