package com.example.growwth

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class FillRoadmapAdapter(private var headingList: ArrayList<String>, private var context : Context, private val onItemClicked: (String, Int) -> Unit) : RecyclerView.Adapter<FillRoadmapAdapter.RoadmapViewHolder>(){
    inner class RoadmapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var roadmapHeading: TextView = itemView.findViewById(R.id.roadmapTitle)
        var imageView : ImageView = itemView.findViewById(R.id.openBtn)
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
        val item = headingList[position]
        holder.roadmapHeading.text = item

        holder.imageView.setOnClickListener {
            onItemClicked(item, position)
        }
        holder.cardView.setOnClickListener {
            Toast.makeText(context, "You selected ${holder.roadmapHeading.text}", Toast.LENGTH_SHORT).show()
        }
    }

    fun removeItem(position: Int) {
        headingList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,headingList.size)
    }

    fun addItem(item: String) {
        headingList.add(item)
        notifyItemInserted(headingList.size - 1)
    }

}