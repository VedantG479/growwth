package com.example.growwth

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class CalendarData(var data : Date, var isSelected : Boolean = false) {
    val calendarDay : String
        get() = SimpleDateFormat("EE", Locale.getDefault()).format(data)

    val calendarDate : String
        get() {
            val cal = Calendar.getInstance()
            cal.time = data
            return cal[Calendar.DAY_OF_MONTH].toString()
        }
}