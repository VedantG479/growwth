package com.example.growwth.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.growwth.Step
import com.example.growwth.StepAdapter
import com.example.growwth.databinding.FragmentRoadmapDetailBinding
import com.google.firebase.database.*

class RoadmapDetail : Fragment() {

    private lateinit var binding: FragmentRoadmapDetailBinding
    private lateinit var stepsAdapter: StepAdapter
    private lateinit var databaseReference: DatabaseReference

    companion object {
        private const val ARG_ROADMAP_NAME = "roadmapName"

        fun newInstance(roadmapName: String) = RoadmapDetail().apply {
            arguments = Bundle().apply {
                putString(ARG_ROADMAP_NAME, roadmapName)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoadmapDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val roadmapName = arguments?.getString(ARG_ROADMAP_NAME) ?: ""
        binding.roadmapTitle.text = roadmapName
        binding.stepsRecyclerView.layoutManager = LinearLayoutManager(context)
        stepsAdapter = StepAdapter(arrayListOf())
        binding.stepsRecyclerView.adapter = stepsAdapter

        fetchRoadmapDetails(roadmapName)
    }

    private fun fetchRoadmapDetails(roadmapName: String) {
        databaseReference = FirebaseDatabase.getInstance().reference.child("roadmaps").child(roadmapName)
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("RoadmapDetailFragment", "Data snapshot: ${snapshot.value}")
                val stepsList = arrayListOf<Step>()
                for (stepSnapshot in snapshot.children) {
                    if (stepSnapshot.key!!.startsWith("step")) {
                        val step = stepSnapshot.getValue(Step::class.java)
                        if (step != null) {
                            stepsList.add(step)
                        }
                    }
                }
                Log.d("RoadmapDetailFragment", "Steps list size: ${stepsList.size}")
                stepsAdapter.updateList(stepsList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("RoadmapDetailFragment", "Error fetching roadmap details: ${error.message}")
            }
        })
    }
}
