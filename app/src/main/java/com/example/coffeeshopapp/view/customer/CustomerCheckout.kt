package com.example.coffeeshopapp.view.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.ActivityCustomerCheckoutBinding

class CustomerCheckout : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitOrderButton.setOnClickListener {
            submitOrder()
        }
    }

    private fun submitOrder() {
        val fullName = binding.fullName.text.toString()
        val address = binding.address.text.toString()
        val paymentMethod = when (binding.paymentMethod.checkedRadioButtonId) {
            R.id.radioCard -> "Card"
            R.id.radioCash -> "Cash"
            else -> ""
        }

        processOrder(fullName, address, paymentMethod)
    }

    private fun processOrder(fullName: String, address: String, paymentMethod: String) {
        // Convert cart to order
        // Set order status to 'pending'
        // Store payment details in database
        // Clear the cart

        // Navigate to a confirmation screen or show a confirmation message
    }
}