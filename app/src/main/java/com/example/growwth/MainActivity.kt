package com.example.growwth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.growwth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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