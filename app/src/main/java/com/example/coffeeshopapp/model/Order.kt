package com.example.coffeeshopapp.model

data class Order(
    val id: String,
    val userId: String,
    val items: List<CartItem>,
    val orderTime: Long = System.currentTimeMillis(),
    val totalAmount: Double,
    val status: String
)