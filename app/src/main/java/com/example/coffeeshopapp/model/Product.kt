package com.example.coffeeshopapp.model
data class Product @JvmOverloads constructor(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val category: String = ""
)