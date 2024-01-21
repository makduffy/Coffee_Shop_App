package com.example.coffeeshopapp.view.customer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.databinding.ActivityCustomerSignUpBinding
import com.example.coffeeshopapp.model.Customer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CustomerSignUp : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnUserSignUp.setOnClickListener {
            val userName = binding.userName.text.toString()
            val userEmail = binding.userEmail.text.toString()
            val userPhoneNo = binding.userPhoneNo.text.toString()
            val userPassword = binding.userPassword.text.toString()

            if (userEmail.isNotEmpty() && userPassword.isNotEmpty()) {
                createUser(userEmail, userPassword, userName, userPhoneNo)
            } else {
                Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createUser(email: String, password: String, name: String, phone: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                    database = FirebaseDatabase.getInstance().getReference("Customer")
                    val customer = Customer(name, email, phone, password)
                    database.child(userId).setValue(customer).addOnSuccessListener {
                        Toast.makeText(this, "Signed up successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, CustomerSignIn::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(this, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}