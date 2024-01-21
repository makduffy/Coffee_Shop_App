package com.example.coffeeshopapp.model

import java.util.Date

data class Order(
    val orderId: String = "",
    val customerId: String = "",
    val items: List<CartItem> = emptyList(),
    val orderDate: Date = Date(),
    val totalAmount: Double = 0.0,
    val status: String = "Processing"
)