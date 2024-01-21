package com.example.coffeeshopapp.view.customer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.databinding.ActivitySelectedProductBinding
import com.example.coffeeshopapp.model.CartItem
import com.example.coffeeshopapp.model.Product
import com.example.coffeeshopapp.view_model.customer.CartViewModel
import com.example.coffeeshopapp.view_model.customer.MenuProductsViewModel

class SelectedProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectedProductBinding
    private val cartViewModel = CartViewModel()
    private val menuViewModel = MenuProductsViewModel()
    private var currentProduct: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getStringExtra("productId")
        productId?.let {
            menuViewModel.getProductById(it) { product ->
                product?.let {
                    currentProduct = it
                    displayProductDetails(it)
                }
            }
        }
        binding.btnAddToCart.setOnClickListener {
            addToCart()
        }
        binding.btnViewCart.setOnClickListener {
            startActivity(Intent(this, CustomerViewCart::class.java))
        }
        binding.btnReturnToMenu.setOnClickListener {
            startActivity(Intent(this, CustomerMenu::class.java))
        }
    }
    private fun addToCart() {
        val quantity = binding.quantity.text.toString().toIntOrNull() ?: 1
        currentProduct?.let {
            val cartItem = CartItem(it.id, quantity)
            cartViewModel.addToCart(cartItem)
            Toast.makeText(this, "${it.name} added to cart.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun displayProductDetails(product: Product?){
        product?.let {
            binding.productName.text = it.name
            binding.productDescription.text = it.description
            binding.productPrice.text = String.format("£%.2f", it.price)
        }
    }
}

