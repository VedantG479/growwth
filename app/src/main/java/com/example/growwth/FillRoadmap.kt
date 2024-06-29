package com.example.growwth
object FillRoadmap {
    private var headingList1 : ArrayList<String> = ArrayList()
    private var headingList2 : ArrayList<String> = ArrayList()
    fun getData1() : ArrayList<String>{
        headingList1.add("Android")
        headingList1.add("Full Stack")
        headingList1.add("FrontEnd")

        return headingList1
    }

    fun getData2() : ArrayList<String>{
        headingList2.add("Android")
        headingList2.add("Full Stack")
        headingList2.add("FrontEnd")

        return headingList2
    }
}