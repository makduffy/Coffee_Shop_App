package com.example.coffeeshopapp.model

data class Feedback(
    val customerId: String,
    val rating: Float,
    val comment: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)