package com.example.growwth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.growwth.FillRoadmap
import com.example.growwth.FillRoadmapAdapter
import com.example.growwth.R
class Roadmaps : Fragment() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_roadmaps, container, false)
        recyclerView = view.findViewById(R.id.recyclerView1)
        val headingsList = FillRoadmap.getData()

        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = FillRoadmapAdapter(headingsList, requireContext())
        recyclerView.adapter = adapter

        return view
    }
}