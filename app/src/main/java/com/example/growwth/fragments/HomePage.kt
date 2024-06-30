package com.example.growwth.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.growwth.CalendarAdapter
import com.example.growwth.CalendarData
import com.example.growwth.R
import com.example.growwth.databinding.FragmentHomePageBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomePage : Fragment(), CalendarAdapter.CalendarInterface {
    private lateinit var binding: FragmentHomePageBinding
    private val sdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val cal = Calendar.getInstance(Locale.ENGLISH)
    private var mStartD: Date? = null

    private val calendarAdapter = CalendarAdapter(this, arrayListOf())
    private val calendarList = ArrayList<CalendarData>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomePageBinding.inflate(layoutInflater)

        init()
        initCalendar()
        return binding.root
    }

    private fun init(){
        binding.apply {
            monthYearPicker.text = sdf.format(cal.time)
            calendarView.setHasFixedSize(true)
            calendarView.adapter = calendarAdapter
            monthYearPicker.setOnClickListener{
                displayDatePicker()
            }
        }
    }

    private fun initCalendar(){
        mStartD = Date()
        cal.time = Date()
        getDates()
    }

    private fun displayDatePicker(){
        val materialDateBuilder: MaterialDatePicker.Builder<Long> = MaterialDatePicker.Builder.datePicker()
        materialDateBuilder.setTitleText("Select Date")
        val materialDatePicker = materialDateBuilder.build()
        materialDatePicker.show(parentFragmentManager, "MATERIAL_DATE_PICKER")
        materialDatePicker.addOnPositiveButtonClickListener {
            try{
                mStartD = Date(it)
                binding.monthYearPicker.text = sdf.format(it)
                cal.time = Date(it)

                getDates()
            }
            catch (e: ParseException){
                Log.e("MAIN_ACTIVITY", "displayDatepicker: ${e.message}")
            }
        }
    }

    private fun getDates(){
        val dateList = ArrayList<CalendarData>()
        val dates = ArrayList<Date>()
        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)

        while(dates.size < maxDaysInMonth){
            dates.add(monthCalendar.time)
            dateList.add(CalendarData(monthCalendar.time))
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        calendarList.clear()
        calendarList.addAll(dateList)
        calendarAdapter.updateList(dateList)

        for(item in dateList.indices){
            if(dateList[item].data == mStartD){
                calendarAdapter.setPosition(item)
                binding.calendarView.scrollToPosition(item)
            }
        }
    }

    override fun onSelect(calendarData: CalendarData, position: Int, day: TextView) {
        calendarList.forEachIndexed { index, calendarData ->
            calendarData.isSelected = index == position
        }

        calendarAdapter.updateList(calendarList)
    }
}