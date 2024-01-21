package com.example.coffeeshopapp.view.customer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshopapp.databinding.ActivityCustomerMenuBinding
import com.example.coffeeshopapp.support.ProductAdapter
import com.example.coffeeshopapp.view_model.customer.MenuProductsViewModel

class CustomerMenu : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerMenuBinding
    private lateinit var productAdapter: ProductAdapter
    private val menuModel = MenuProductsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryButtons()
        setupRecyclerView()

        binding.btnCart.setOnClickListener {
            startActivity(Intent(this, CustomerViewCart::class.java))
        }
        binding.btnMenu.setOnClickListener {
            startActivity(Intent(this, CustomerMenu::class.java))
        }

    }
    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(listOf())
        binding.productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CustomerMenu)
            adapter = productAdapter
        }
    }

    private fun categoryButtons() {
        binding.btnHotDrinks.setOnClickListener { loadProducts("hot_drinks") }
        binding.btnColdDrinks.setOnClickListener { loadProducts("cold_drinks") }
        binding.btnCakes.setOnClickListener { loadProducts("cakes") }
    }

    private fun loadProducts(category: String) {
        menuModel.getProductsByCategory(category) { products ->
            productAdapter.updateProducts(products)
        }
    }

}
