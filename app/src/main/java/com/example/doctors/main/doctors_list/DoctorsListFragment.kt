package com.example.doctors.main.doctors_list

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctors.R
import com.example.doctors.databinding.FragmentDoctorsListBinding
import com.example.doctors.main.doctors_list.data.Doctor
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.getScopeId


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
        configureListener()
        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater) {
        binding = FragmentDoctorsListBinding.inflate(inflater)
        binding.viewModel = viewModel
    }

    private fun configurationLiveDataObservers() {
        viewModel.openSignInFragment.observe(viewLifecycleOwner) {
            if (it) {
                requireActivity().supportFragmentManager
                    .findFragmentById(R.id.myNavHost)!!.findNavController()
                    .navigate(R.id.signInFragment)
            }
        }

        viewModel.openAppointmentFragment.observe(viewLifecycleOwner) { doctorId ->
            val arg = Bundle()
            arg.putString("userId", viewModel.getUser()!!.getScopeId())
            arg.putString("doctorId", doctorId)
            findNavController().navigate(R.id.action_doctorsAppointmentFragment_to_appointmentFragment2, arg)
        }

        viewModel.options.observe(viewLifecycleOwner) { options ->
            options?.let { configureRecyclerView(options) }
        }
    }

    private fun configureListener() {
        binding.sortSpinner.onItemSelectedListener =
            object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {

                    when (position) {
                        0 -> viewModel.getOptionsForDoctorsList(viewLifecycleOwner, "rating", true)
                        1 -> viewModel.getOptionsForDoctorsList(viewLifecycleOwner, "rating", false)
                        2 -> viewModel.getOptionsForDoctorsList(
                            viewLifecycleOwner,
                            "avaragePrice",
                            true
                        )
                        3 -> viewModel.getOptionsForDoctorsList(
                            viewLifecycleOwner,
                            "avaragePrice",
                            false
                        )
                    }

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}

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
            addItemDecoration(
                DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
                    .apply { setDrawable(context.getDrawable(R.drawable.line_for_recycler)!!) })
        }

    }

}