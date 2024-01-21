package com.example.coffeeshopapp.model

import java.io.Serializable
data class CartItem(
    val productId: String = "",
    val quantity: Int = 0
)