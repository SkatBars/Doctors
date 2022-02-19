package com.example.doctors.main.doctorsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.databinding.DoctorsListItemBinding
import com.example.doctors.main.doctorsList.data.Doctor
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class DoctorsListAdapter(
    options: FirestoreRecyclerOptions<Doctor>,
    private val viewModel: DoctorsListViewModel
    ) : FirestoreRecyclerAdapter<Doctor, DoctorsListAdapter
        .DoctorsHolder>(options) {

    class DoctorsHolder(
        private val binding: DoctorsListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: DoctorsListViewModel, doctor: Doctor) {
            binding.doctor = doctor
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DoctorsHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DoctorsListItemBinding.inflate(layoutInflater, parent, false)
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