package com.example.doctors.autorization.signIn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.doctors.RC_SIGN_IN
import com.example.doctors.databinding.FragmentSignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    private val viewModel: SignInViewModel by viewModel()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater)
        configurationClickListeners()
        configurationLiveDataObservers()

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                viewModel.signInWithGoogle(account.idToken!!)
            }catch (e: ApiException) {
                showSnackbar(e.message.toString())
            }
        }
    }

    private fun configurationClickListeners() {
        with(binding) {
            signInGoogleBtn.setOnClickListener { showGoogleAuthorizationActivity() }
            signInBtn.setOnClickListener {
                val email = emailEditTextSignIn.text.toString()
                val password = passwordEditTextSignIn.text.toString()
                viewModel.signInWithEmail(email, password)
            }
        }
    }

    private fun configurationLiveDataObservers() {
        viewModel.openMainFragmentEvent.observe(viewLifecycleOwner) { }
        viewModel.showSnackbar.observe(viewLifecycleOwner) { showSnackbar(it) }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun showGoogleAuthorizationActivity() {
        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), viewModel.gsoDb)
        val signInGoogleIntent = googleSignInClient.signInIntent
        startActivityForResult(signInGoogleIntent, RC_SIGN_IN)
    }

}