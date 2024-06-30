package com.example.growwth
object FillRoadmap {
    private var headingList1 : ArrayList<String> = ArrayList()
    private var headingList2 : ArrayList<String> = ArrayList()
    fun getData1() : ArrayList<String>{
        return headingList1
    }

    fun getData2() : ArrayList<String>{
        headingList2.add("Android")
        headingList2.add("FullStack")
        headingList2.add("DevOps")

        return headingList2
    }
}

