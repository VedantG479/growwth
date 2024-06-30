package com.example.growwth

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.growwth.databinding.DateCardBinding

class CalendarAdapter(private val calendarInterface : CalendarInterface, private val list : ArrayList<CalendarData>) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {
    var pos = -1
    interface CalendarInterface{
        fun onSelect(calendarData: CalendarData, position: Int, day: TextView)
    }

    inner class ViewHolder(private val binding: DateCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(calendarData: CalendarData, position: Int){
            val calendarDay = binding.tvCalendarDay
            val calendarDate = binding.tvCalendarDate
            val cardView = binding.root

            if(pos == position){
                calendarData.isSelected = true
            }
            if(calendarData.isSelected){
                pos = -1
                calendarDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                calendarDate.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                cardView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.bottombar))
            }
            else{
                calendarDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.lighterbar))
                calendarDate.setTextColor(ContextCompat.getColor(itemView.context, R.color.lighterbar))
                cardView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
            }

            calendarDay.text = calendarData.calendarDay
            calendarDate.text = calendarData.calendarDate

            cardView.setOnClickListener {
                calendarInterface.onSelect(calendarData, adapterPosition, calendarDate)
            }
        }
    }

    fun setPosition(position : Int){
        this.pos = position
    }

    fun updateList(calendarList: ArrayList<CalendarData>){
        list.clear()
        list.addAll(calendarList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DateCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }
}