package com.example.coffeeshopapp.model

import java.util.Date

data class Order(
    var orderId: String? = null,
    var items: List<CartItem>? = null,
    var status: String? = "pending",
    var orderDate: Long? = System.currentTimeMillis(),
    var paymentMethod: String? = "",
    var customerDetails: Customer? = null

)