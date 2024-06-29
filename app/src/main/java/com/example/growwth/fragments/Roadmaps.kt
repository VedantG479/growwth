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
    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView

    private lateinit var adapter1: FillRoadmapAdapter
    private lateinit var adapter2: FillRoadmapAdapter
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_roadmaps, container, false)
        recyclerView1 = view.findViewById(R.id.recyclerView1)
        recyclerView2 = view.findViewById(R.id.recyclerView2)
        val headingsList1 = FillRoadmap.getData1()
        val headingList2 = FillRoadmap.getData2()

        recyclerView1.layoutManager = LinearLayoutManager(context)
        recyclerView2.layoutManager = LinearLayoutManager(context)

        adapter1 = FillRoadmapAdapter(headingsList1, requireContext(), parentFragmentManager, R.id.fragmentContainer) { item, position ->
            moveItem(item, position, adapter1, adapter2)
        }
        adapter2 = FillRoadmapAdapter(headingList2, requireContext(), parentFragmentManager, R.id.fragmentContainer) { item, position ->
            moveItem(item, position, adapter2, adapter1)
        }

        recyclerView1.adapter = adapter1
        recyclerView2.adapter = adapter2

        return view
    }

    private fun moveItem(item: String, position: Int, fromAdapter: FillRoadmapAdapter, toAdapter: FillRoadmapAdapter){
        fromAdapter.removeItem(position)
        toAdapter.addItem(item)
    }
}