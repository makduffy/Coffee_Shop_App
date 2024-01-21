package com.example.coffeeshopapp.view.customer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.databinding.ActivityCustomerSignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class CustomerSignIn : AppCompatActivity() {

    private lateinit var binding : ActivityCustomerSignInBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnSignIn.setOnClickListener{
            val userName = binding.userEmail.text.toString()
            val userPassword = binding.userPassword.text.toString()
            signIn(userName, userPassword)
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, CustomerMenu::class.java))
                } else {
                    Toast.makeText(baseContext, "Incorrect login, try again",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}