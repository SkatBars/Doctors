package com.example.doctors.main.doctors_list.appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctors.databinding.AppointmentFragmentBinding
import com.example.doctors.main.doctors_list.data.PlaceToWrite
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppointmentFragment : Fragment() {

    private val viewModel: AppointmentViewModel by viewModel()
    private lateinit var  binding: AppointmentFragmentBinding
    private lateinit var adapter: AppointmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureRecyclerView()
        configureObserverLiveData()
        binding = AppointmentFragmentBinding.inflate(inflater)
        return binding.root
    }

    private fun configureObserverLiveData() {
        viewModel.places.observe(viewLifecycleOwner, Observer {
            changeListRecyclerView(it)
        })
    }

    private fun configureRecyclerView() {
        adapter = AppointmentAdapter()

        with(binding.appointmentRecyclerView) {
            adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun changeListRecyclerView(newListWithPlaces: List<PlaceToWrite>) {
        adapter.changeItems(newListWithPlaces)
    }


}