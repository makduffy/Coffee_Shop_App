package com.example.coffeeshopapp.model.customer

import android.util.Log
import com.example.coffeeshopapp.model.Order
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OrderModel {

    private val database = FirebaseDatabase.getInstance()

    fun createOrder(order: Order, callback: (String?) -> Unit) {
        val orderRef = database.getReference("Order")
        val orderId = orderRef.push().key

        if (orderId != null) {
            order.orderId = orderId
            orderRef.child(orderId).setValue(order)
                .addOnSuccessListener {
                    Log.d("OrderModel", "Order created successfully with ID: $orderId")
                    callback(orderId)
                }
                .addOnFailureListener { exception ->
                    Log.e("OrderModel", "Order creation failed", exception)
                    callback(null)
                }
        } else {
            Log.e("OrderModel", "Order ID is null")
            callback(null)
        }
    }

    fun getOrder(userId: String, callback: (List<Order>) -> Unit) {
        val ordersRef = database.getReference("Order")
        val query = ordersRef.orderByChild("customerId").equalTo(userId)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val orders = mutableListOf<Order>()
                for (snapshot in dataSnapshot.children) {
                    val order = snapshot.getValue(Order::class.java)
                    order?.let { orders.add(it) }
                }
                callback(orders)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("OrderModel", "loadOrders:onCancelled", databaseError.toException())
            }
        })
    }

}
