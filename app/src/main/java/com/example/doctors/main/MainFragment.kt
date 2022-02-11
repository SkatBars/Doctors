package com.example.doctors.main

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.doctors.databinding.FragmentMainBinding
import com.google.android.gms.dynamic.SupportFragmentWrapper

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)
        val navController= childFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment_my)!!
            .findNavController()

        binding.bottomNavigation.setupWithNavController(navController)

        return binding.root
    }

}