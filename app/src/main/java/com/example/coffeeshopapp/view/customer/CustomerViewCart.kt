package com.example.coffeeshopapp.view.customer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshopapp.databinding.ActivityCustomerCartBinding
import com.example.coffeeshopapp.support.CartItemAdapter
import com.example.coffeeshopapp.view_model.customer.CartViewModel

class CustomerViewCart : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerCartBinding
    private lateinit var cartAdapter: CartItemAdapter
    private val cartViewModel = CartViewModel.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCustomerCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadCartItems()
        setUpListeners()

    }
    private fun setUpListeners(){
        binding.btnPayment.setOnClickListener {
            startActivity(Intent(this, CustomerCheckout::class.java))
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
    private fun setupRecyclerView() {
        cartAdapter = CartItemAdapter(mutableListOf()) { cartItem ->
                cartViewModel.deleteCartItem(cartItem)
                cartAdapter.deleteItem(cartItem)

        }
        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CustomerViewCart)
            adapter = cartAdapter
        }
    }
    private fun loadCartItems() {
        val cartItemsWithQuantity = cartViewModel.getCartItems()

        runOnUiThread {
            val items = cartItemsWithQuantity.map { it.first }.toMutableList()
            cartAdapter.updateData(items)
        }
    }
    override fun onResume() {
        super.onResume()
        loadCartItems()
    }

}



