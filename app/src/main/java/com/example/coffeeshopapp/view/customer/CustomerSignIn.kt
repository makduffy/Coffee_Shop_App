package com.example.coffeeshopapp.view.customer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.databinding.ActivityCustomerSignInBinding
import com.example.coffeeshopapp.model.Customer
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CustomerSignIn : AppCompatActivity() {

    private lateinit var binding : ActivityCustomerSignInBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignIn.setOnClickListener{
            val userName = binding.username.text.toString()
            val userPassword = binding.password.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Customer")
            val customers = Customer(userName, userPassword)
            database.child(userName).setValue(customers).addOnSuccessListener{

                binding.username.text.clear()
                binding.password.text.clear()

                Toast.makeText(this, "Signed up successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

}