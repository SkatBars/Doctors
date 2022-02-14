package com.example.doctors.main.doctorsAppointment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.databinding.DoctorsListItemBinding
import com.example.doctors.main.doctorsAppointment.data.Doctor
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class DoctorsListAdapter(
    private val viewModel: DoctorsListViewModel
    ) : FirestoreRecyclerAdapter<Doctor, DoctorsListAdapter.DoctorsHolder>(viewModel.getOptionsForDoctorsList()) {

    class DoctorsHolder(
        private val binding: DoctorsListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: DoctorsListViewModel, doctor: Doctor) {
            binding.doctor = doctor
            binding.viewModel = viewModel
        }

        companion object {
            fun from(parent: ViewGroup): DoctorsHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DoctorsListItemBinding.inflate(layoutInflater)
                return DoctorsHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsHolder {
        return DoctorsHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DoctorsHolder, position: Int, model: Doctor) {
        holder.bind(viewModel, model)
    }
}