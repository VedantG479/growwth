package com.example.growwth
object FillRoadmap {
    private var headingList : ArrayList<String> = ArrayList()
    fun getData() : ArrayList<String>{
        headingList.add("Android")
        headingList.add("Full Stack")
        headingList.add("FrontEnd")
        headingList.add("BackEnd")
        headingList.add("DevOps")
        headingList.add("Blockchain")

        return headingList
    }
}