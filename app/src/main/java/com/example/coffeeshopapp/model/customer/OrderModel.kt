package com.example.coffeeshopapp.model.customer

import android.util.Log
import com.example.coffeeshopapp.model.Order
import com.google.firebase.database.FirebaseDatabase

class OrderModel {

    private val database = FirebaseDatabase.getInstance()

    fun createOrder(order: Order, callback: (Boolean) -> Unit) {
        val orderRef = database.getReference("Order")
        val orderId = orderRef.push().key
        Log.d("OrderModel", "Creating order: $order")
        orderId?.let {
            orderRef.child(it).setValue(order)
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener {
                    callback(false)
                }
        } ?: callback(false)
    }

    fun clearCart(userId: String, callback: (Boolean) -> Unit) {
        val cartRef = database.getReference("Cart").child(userId)
        cartRef.removeValue()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener {
                callback(false)
            }
    }
}
