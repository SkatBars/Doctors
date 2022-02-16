package com.example.doctors.main.doctorsAppointment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctors.R
import com.example.doctors.databinding.FragmentDoctorsListBinding
import com.example.doctors.main.doctorsAppointment.data.Doctor
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class DoctorsListFragment : Fragment() {

    private val viewModel: DoctorsListViewModel by viewModel()
    private lateinit var binding: FragmentDoctorsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getOptionsForDoctorsList(this)
        configureBinding(inflater)
        configurationLiveDataObservers()
        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater) {
        binding = FragmentDoctorsListBinding.inflate(inflater)
        binding.viewModel = viewModel
    }

    private fun configurationLiveDataObservers() {
        viewModel.openSignInFragment.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(R.id.action_doctorsAppointmentFragment_to_navigation  )
        }
        viewModel.options.observe(viewLifecycleOwner) { options ->
            options?.let {configureRecyclerView(options)}
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun configureRecyclerView(options: FirestoreRecyclerOptions<Doctor>) {
        with(binding.recyclerDoctors) {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = DoctorsListAdapter(options, viewModel)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
                .apply { setDrawable(context.getDrawable(R.drawable.line_for_recycler)!!) })
        }

    }

}