package com.example.coffeeshopapp.model

import android.icu.util.Calendar
import java.util.Date

data class Order(
    var customerId: String? = null,
    var items: Map<String, CartItem>? = null,
    var status: String? = "pending",
    var date: Long = Calendar.getInstance().timeInMillis,
    var orderId: String? = null
)