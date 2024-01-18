package com.example.coffeeshopapp.view.customer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.databinding.ActivitySignUpBinding
import com.example.coffeeshopapp.model.Customer
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CustomerSignUp : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUserSignUp.setOnClickListener{
            val firstName = binding.firstName.text.toString()
            val lastName = binding.lastName.text.toString()
            val userName = binding.username.text.toString()
            val userEmail = binding.email.text.toString()
            val userPhoneNo = binding.phoneNo.text.toString()
            val userPassword = binding.password.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Customer")
            val customers = Customer(firstName, lastName, userName, userEmail, userPhoneNo, userPassword)
            database.child(userName).setValue(customers).addOnSuccessListener{

                binding.firstName.text.clear()
                binding.lastName.text.clear()
                binding.email.text.clear()
                binding.phoneNo.text.clear()
                binding.username.text.clear()
                binding.password.text.clear()

                Toast.makeText(this, "Signed up successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

}