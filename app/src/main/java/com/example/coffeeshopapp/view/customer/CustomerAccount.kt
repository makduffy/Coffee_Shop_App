package com.example.coffeeshopapp.view.customer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.databinding.ActivityCustomerAccountBinding
import com.example.coffeeshopapp.model.Customer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CustomerAccount : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchCustomerDetails()

        binding.btnSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnCart.setOnClickListener {
            startActivity(Intent(this, CustomerViewCart::class.java))
        }
        binding.btnMenu.setOnClickListener {
            startActivity(Intent(this, CustomerMenu::class.java))
        }
        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, CustomerAccount::class.java))
        }
    }

    private fun fetchCustomerDetails() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val databaseReference = FirebaseDatabase.getInstance().getReference("Customer")

        databaseReference.child(userId).get().addOnSuccessListener { dataSnapshot ->
            val customerAccount = dataSnapshot.getValue(Customer::class.java)
            customerAccount?.let {
                updateUIWithCustomerDetails(it)
            }
        }.addOnFailureListener {
        }
    }

    private fun updateUIWithCustomerDetails(customer: Customer) {
        binding.customerName.text = customer.name
        binding.customerEmail.text = customer.email
        binding.customerPhone.text = customer.phoneNo
    }
}
