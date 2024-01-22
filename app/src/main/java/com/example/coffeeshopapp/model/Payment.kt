package com.example.coffeeshopapp.model

import android.icu.util.Calendar

data class Payment(
    var orderId: String = "",
    var totalPrice: Double = 0.0,
    var paymentType: String = "",
    var paymentDate: Long = Calendar.getInstance().timeInMillis
)