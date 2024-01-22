package com.example.coffeeshopapp.view.customer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        Log.d("CustomerCheckout", "onCreate called")
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitOrderButton.setOnClickListener {
            submitOrder()
        }
    }

    private fun submitOrder() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        binding.submitOrderButton.isEnabled = false
        val name = binding.fullName.text.toString()
        val address = binding.address.text.toString()
        val paymentMethod = getPaymentMethod()

        if (validation(name, address, paymentMethod)) {
            processOrder(userId, name, address, paymentMethod)
        } else {
            binding.submitOrderButton.isEnabled = true
        }
    }


    private fun processOrder(userId: String, name: String, address: String, paymentMethod: String) {
        orderViewModel.convertCartToOrder(userId, name, address, paymentMethod) { success ->
            if (success) {
                runOnUiThread {
                    val intent = Intent(this, CustomerOrderSubmitted::class.java)
                    startActivity(intent)
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this, "Order submission failed", Toast.LENGTH_SHORT).show()
                    binding.submitOrderButton.isEnabled = true
                }
            }
        }
    }


    private fun getPaymentMethod(): String {
        return when (binding.paymentMethod.checkedRadioButtonId) {
            R.id.radioCard -> "Card"
            R.id.radioCash -> "Cash"
            else -> ""
        }
    }

    private fun validation(name: String, address: String, paymentMethod: String): Boolean {
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show()
            return false
        }
        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT).show()
            return false
        }
        if (paymentMethod.isEmpty()) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
        }
    }