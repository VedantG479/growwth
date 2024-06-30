package com.example.growwth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.growwth.FillRoadmap
import com.example.growwth.FillRoadmapAdapter
import com.example.growwth.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Roadmaps : Fragment() {
    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView

    private lateinit var adapter1: FillRoadmapAdapter
    private lateinit var adapter2: FillRoadmapAdapter

    private val roadmapsListFollowing = arrayListOf<String>()
    private val roadmapsListNotFollowing = arrayListOf<String>()

    private lateinit var database: DatabaseReference
    private lateinit var userId: String
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_roadmaps, container, false)
        database = FirebaseDatabase.getInstance().reference
        userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        recyclerView1 = view.findViewById(R.id.recyclerView1)
        recyclerView2 = view.findViewById(R.id.recyclerView2)

        recyclerView1.layoutManager = LinearLayoutManager(context)
        recyclerView2.layoutManager = LinearLayoutManager(context)

        adapter1 = FillRoadmapAdapter(roadmapsListFollowing, requireContext(), parentFragmentManager, R.id.fragmentContainer) { item, position ->
            moveItem(item, position, adapter1, adapter2, false)
        }
        adapter2 = FillRoadmapAdapter(roadmapsListNotFollowing, requireContext(), parentFragmentManager, R.id.fragmentContainer) { item, position ->
            moveItem(item, position, adapter2, adapter1, true)
        }

        recyclerView1.adapter = adapter1
        recyclerView2.adapter = adapter2

        fetchRoadmaps()

        return view
    }
    private fun fetchRoadmaps() {
        val roadmapsRef = database.child("roadmaps")
        roadmapsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val roadmaps = mutableListOf<String>()
                val userFollowingRoadmaps = mutableListOf<String>()

                for (data in snapshot.children) {
                    val roadmapName = data.child("name").getValue(String::class.java) ?: ""
                    roadmaps.add(roadmapName)
                }

                val userRef = database.child("users").child(userId).child("followingRoadmaps")
                userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(userSnapshot: DataSnapshot) {
                        for (data in userSnapshot.children) {
                            val roadmapId = data.key ?: ""
                            if (data.value as Boolean) {
                                userFollowingRoadmaps.add(roadmapId)
                            }
                        }

                        roadmapsListFollowing.clear()
                        roadmapsListNotFollowing.clear()

                        for (roadmap in roadmaps) {
                            if (userFollowingRoadmaps.contains(roadmap)) {
                                roadmapsListFollowing.add(roadmap)
                            } else {
                                roadmapsListNotFollowing.add(roadmap)
                            }
                        }

                        adapter1.notifyDataSetChanged()
                        adapter2.notifyDataSetChanged()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(requireContext(), "Failed to fetch user's following roadmaps", Toast.LENGTH_SHORT).show()
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Failed to fetch roadmaps", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun moveItem(item: String, position: Int, fromAdapter: FillRoadmapAdapter, toAdapter: FillRoadmapAdapter, isFollowing : Boolean){
        fromAdapter.removeItem(position)
        toAdapter.addItem(item)

        val userRef = database.child("users").child(userId).child("followingRoadmaps").child(item)
        userRef.setValue(isFollowing)
    }
}