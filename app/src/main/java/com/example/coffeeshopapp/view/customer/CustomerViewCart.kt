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
    private val cartViewModel = CartViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        try{
            super.onCreate(savedInstanceState)
            binding = ActivityCustomerCartBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupRecyclerView()
            loadCartItems()

            binding.btnReturnToMenu.setOnClickListener {
                startActivity(Intent(this, CustomerMenu::class.java))
            }

            binding.btnPayment.setOnClickListener {
                startActivity(Intent(this, CustomerCheckout::class.java))
            }
        } catch (e: Exception) {
            Log.e("CustomerViewCart", "CustomerViewCart activity couldn't run", e)
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
        cartViewModel.getCartItems { items ->
            runOnUiThread {
                cartAdapter.updateData(items.toMutableList())
            }
        }
    }
}

