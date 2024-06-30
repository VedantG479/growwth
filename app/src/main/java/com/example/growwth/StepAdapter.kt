package com.example.growwth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StepAdapter(private var stepsList: List<Step>) : RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

    class StepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.stepTitle)
        val desc: TextView = itemView.findViewById(R.id.stepDesc)
        val timeReqd: TextView = itemView.findViewById(R.id.stepTimeReqd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_step, parent, false)
        return StepViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step = stepsList[position]
        holder.title.text = step.title
        holder.desc.text = step.desc
        holder.timeReqd.text = "Time Required: ${step.timeReqd} hours"
    }

    override fun getItemCount(): Int = stepsList.size

    fun updateList(newStepsList: List<Step>) {
        stepsList = newStepsList
        notifyDataSetChanged()
    }
}
