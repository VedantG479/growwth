package com.example.growwth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.growwth.databinding.ActivityMainBinding
import com.example.growwth.fragments.HomePage
import com.example.growwth.fragments.Roadmaps
import com.example.growwth.fragments.UserProfile

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val roadmapFragment = Roadmaps()
        val homePageFragment = HomePage()
        val userProfileFragment = UserProfile()
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, homePageFragment)
        fragmentTransaction.commit()

        binding.bottomBar.setOnItemSelectedListener {
            when(it){
                0->{
                    val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragmentContainer, roadmapFragment)
                    fragmentTransaction.commit()
                }
                1->{
                    val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragmentContainer, homePageFragment)
                    fragmentTransaction.commit()
                }
                2->{
                    val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fragmentContainer, userProfileFragment)
                    fragmentTransaction.commit()
                }
            }
        }
    }
}