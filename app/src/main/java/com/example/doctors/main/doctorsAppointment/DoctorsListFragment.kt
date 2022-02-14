package com.example.doctors.main.doctorsAppointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.doctors.R
import com.example.doctors.databinding.FragmentDoctorsListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DoctorsListFragment : Fragment() {

    private val viewModel: DoctorsListViewModel by viewModel()
    private lateinit var binding: FragmentDoctorsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureBinding(inflater, container)
        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentDoctorsListBinding.inflate(inflater)
        binding.viewModel = viewModel
    }

}