package com.example.growwth.roadmaps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.growwth.R

class FillStepsAdapter(private var stepTitle : ArrayList<String>, private var stepDesc : ArrayList<String>, var context: Context) : RecyclerView.Adapter<FillStepsAdapter.StepViewHolder>(){
    inner class StepViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.stepTitle)
        val desc = itemView.findViewById<TextView>(R.id.stepDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.skill_steps, parent, false)
        return StepViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return stepTitle.size
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.title.text = stepTitle[position]
        holder.desc.text = stepDesc[position]
    }
}