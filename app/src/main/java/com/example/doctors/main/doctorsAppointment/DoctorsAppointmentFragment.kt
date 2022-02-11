package com.example.doctors.main.doctorsAppointment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.doctors.R

class DoctorsAppointmentFragment : Fragment() {

    private lateinit var viewModel: DoctorsAppointmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_doctors_appointment, container, false)
    }
}