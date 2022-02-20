package com.example.doctors.main.doctors_list.appointment

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.doctors.R
import com.example.doctors.databinding.AppointmentItemBinding
import com.example.doctors.main.doctors_list.data.PlaceToWrite

class AppointmentAdapter(private val viewModel: AppointmentViewModel)
    : RecyclerView.Adapter<AppointmentAdapter.AppointmentHolder>() {

    private val places: MutableList<PlaceToWrite> = mutableListOf()

    class AppointmentHolder(private val binding: AppointmentItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

            @RequiresApi(Build.VERSION_CODES.M)
            fun bind(placeToWrite: PlaceToWrite, viewModel: AppointmentViewModel) {
                binding.viewModel = viewModel
                binding.time = placeToWrite.time
                if (placeToWrite.isTaken) bindForTaken()
                else bindForNotTaken()
            }

            @RequiresApi(Build.VERSION_CODES.M)
            private fun bindForTaken() {
                with(binding) {
                    appointmentLayout.setBackgroundColor(
                        appointmentLayout.context.getColor(R.color.red_500))
                    isTakenTextView.visibility = View.VISIBLE
                    takePlaceBtn.visibility = View.INVISIBLE
                }
            }

        @RequiresApi(Build.VERSION_CODES.M)
        private fun bindForNotTaken() {
            with(binding) {
                appointmentLayout.setBackgroundColor(
                    appointmentLayout.context.getColor(R.color.blue_200))
                takePlaceBtn.visibility = View.VISIBLE
                isTakenTextView.visibility = View.INVISIBLE
            }
        }

        companion object {
            fun from(parent: ViewGroup): AppointmentHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = AppointmentItemBinding.inflate(inflater, parent, false)
                return AppointmentHolder(binding)
            }
        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentHolder {
        return AppointmentHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AppointmentHolder, position: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.bind(places[position], viewModel)
        }
    }

    override fun getItemCount(): Int {
        return places.size
    }

    fun changeItems(newListWithPlaces: List<PlaceToWrite>){
        places.clear()
        places.addAll(newListWithPlaces)
        notifyDataSetChanged()
    }

}