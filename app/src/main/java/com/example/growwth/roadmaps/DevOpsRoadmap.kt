package com.example.growwth.roadmaps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.growwth.R
class DevOpsRoadmap : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FillStepsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dev_ops_roadmap, container, false)
        recyclerView = view.findViewById(R.id.recyclerView1)
        val titleList = FillSteps.getTitleDataDevOps()
        val descList = FillSteps.getDescDataDevOps()

        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = FillStepsAdapter(titleList, descList, requireContext())
        recyclerView.adapter = adapter
        return view
    }
}