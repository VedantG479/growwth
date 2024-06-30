package com.example.growwth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.growwth.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserProfile : Fragment() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth : FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_user_profile, container, false)
        val userNameText = view.findViewById<TextView>(R.id.userName)
        val dailyTimeText = view.findViewById<EditText>(R.id.hoursDaily)

        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        currentUser?.let{ task ->
            val detailsReference = databaseReference.child("users").child(task.uid).child("details")
            detailsReference.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val username = snapshot.child("username").getValue(String::class.java)
                        val dailyTime = snapshot.child("dailyTime").getValue(Int::class.java)

                        userNameText.text = username
                        dailyTimeText.setText(dailyTime.toString())
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
        }

        return view
    }
}