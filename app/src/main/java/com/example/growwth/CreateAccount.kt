package com.example.growwth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.growwth.databinding.ActivityCreateAccountBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class CreateAccount : AppCompatActivity() {
    private val binding : ActivityCreateAccountBinding by lazy{
        ActivityCreateAccountBinding.inflate(layoutInflater)
    }
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.registerButton.setOnClickListener{
            val username = binding.userNameET.text.toString()
            val userEmail = binding.userEmailET.text.toString()
            val userPassword = binding.userPasswordET.text.toString()

            if(username.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()){
                Toast.makeText(this, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show();
            }
            else{
                auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                    else {
                        Toast.makeText(this, "Error ${it.exception?.message}", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}