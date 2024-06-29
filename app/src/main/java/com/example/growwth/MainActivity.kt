package com.example.growwth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.growwth.databinding.ActivityMainBinding
import com.example.growwth.fragments.Roadmaps

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val firstFragment = Roadmaps()
        fragmentTransaction.add(R.id.fragmentContainer, firstFragment)
        fragmentTransaction.commit()

        binding.bottomBar.setOnItemSelectedListener {
            when(it){
                0->{
                    Toast.makeText(this, "Roadmap Page", Toast.LENGTH_SHORT).show()
                }
                1->{
                    Toast.makeText(this, "Calendar Page", Toast.LENGTH_SHORT).show()
                }
                2->{
                    Toast.makeText(this, "Profile Page", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}