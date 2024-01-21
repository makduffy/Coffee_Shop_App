package com.example.coffeeshopapp.view.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.ActivityCustomerCheckoutBinding
import com.example.coffeeshopapp.view_model.customer.CartViewModel
import com.example.coffeeshopapp.view_model.customer.OrderViewModel
import com.google.firebase.auth.FirebaseAuth

class CustomerCheckout : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerCheckoutBinding
    private var orderViewModel = OrderViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitOrderButton.setOnClickListener {
            submitOrder()
        }
    }

    private fun submitOrder() {
        val name = binding.fullName.text.toString()
        val address = binding.address.text.toString()
        val paymentMethod = getPaymentMethod()

        if (validation(name, address, paymentMethod)) {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
            processOrder(userId, name, address, paymentMethod)
        } else {
            // Show error message if validation fails
        }
    }

    private fun processOrder(userId: String, name: String, address: String, paymentMethod: String) {
        var cart = orderViewModel.convertCartToOrder(userId, name, address, paymentMethod) { success ->
            if (success) {

            }
        }

        // Set order status to 'pending'
        // Store payment details in database
        // Clear the cart

        // Navigate to a confirmation screen or show a confirmation message
    }

    private fun getPaymentMethod(): String {
        return when (binding.paymentMethod.checkedRadioButtonId) {
            R.id.radioCard -> "Card"
            R.id.radioCash -> "Cash"
            else -> ""
        }
    }
    private fun validation(name: String, address: String, paymentMethod: String): Boolean{
        return true
    }
}