package com.example.coffeeshopapp.view.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.ActivityCustomerMenuBinding

class CustomerMenu : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHotDrinks.setOnClickListener { replaceFragment(ProductsFragment.newInstance("hot_drinks")) }
        binding.btnColdDrinks.setOnClickListener { replaceFragment(ProductsFragment.newInstance("cold_drinks")) }
        binding.btnCakes.setOnClickListener { replaceFragment(ProductsFragment.newInstance("cakes")) }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}