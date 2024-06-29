package com.example.growwth

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class FillRoadmapAdapter(private var headingList: ArrayList<String>, private var context : Context) : RecyclerView.Adapter<FillRoadmapAdapter.RoadmapViewHolder>(){
    inner class RoadmapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var roadmapHeading: TextView = itemView.findViewById(R.id.roadmapTitle)
        var cardView : CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoadmapViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.roadmaps_card, parent, false)
        return RoadmapViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return headingList.size
    }

    override fun onBindViewHolder(holder: RoadmapViewHolder, position: Int) {
        holder.roadmapHeading.text = headingList[position]
        holder.cardView.setOnClickListener {
            Toast.makeText(context, "You selected ${holder.roadmapHeading.text}", Toast.LENGTH_SHORT).show()
        }
    }
}