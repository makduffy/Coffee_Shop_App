package com.example.coffeeshopapp.view.customer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.example.coffeeshopapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUserSignUp.setOnClickListener{
            startActivity(Intent(this, CustomerSignUp::class.java))
        }

        binding.btnSignIn.setOnClickListener{
            startActivity(Intent(this, CustomerSignIn::class.java))
        }

        }

}