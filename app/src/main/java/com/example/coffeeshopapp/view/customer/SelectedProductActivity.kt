package com.example.coffeeshopapp.view.customer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.databinding.ActivitySelectedProductBinding
import com.example.coffeeshopapp.model.FirebaseHelper

class SelectedProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectedProductBinding
    private val firebaseHelper = FirebaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getStringExtra("product_id")
        if (productId == null) {
            Toast.makeText(this, "Product ID is missing.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        firebaseHelper.getProductById(productId) { product ->
            if (product == null) {
                Toast.makeText(this, "Product not found.", Toast.LENGTH_LONG).show()
                finish()
                return@getProductById
            }

            binding.productName.text = product.name
            binding.productDescription.text = product.description
            binding.productPrice.text = String.format("£%.2f", product.price)
        }
    }
}
