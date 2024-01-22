package com.example.coffeeshopapp.model

import java.util.Date

data class Order(
    var customerId: String? = null,
    var items: Map<String, CartItem>? = null,
    var status: String? = "pending",
    var paymentMethod: String? = "",
)