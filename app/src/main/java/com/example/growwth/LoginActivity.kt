package com.example.growwth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.growwth.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth : FirebaseAuth

    override fun onStart() {
        super.onStart()
        val currentUser : FirebaseUser? = auth.currentUser
        if(currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.createAccount.setOnClickListener{
            startActivity(Intent(this, CreateAccount::class.java))
            finish()
        }

        binding.loginButton.setOnClickListener{
            val userEmail = binding.userEmailET.text.toString()
            val userPassword = binding.userPasswordET.text.toString()

            if(userEmail.isEmpty() || userPassword.isEmpty()){
                Toast.makeText(this, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener {
                    if(!it.isSuccessful){
                        Toast.makeText(this, "Try Again! Error: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }

        binding.forgotPassword.setOnClickListener {

        }
    }
}