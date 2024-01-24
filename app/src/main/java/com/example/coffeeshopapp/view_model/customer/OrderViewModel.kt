package com.example.coffeeshopapp.view_model.customer

import com.example.coffeeshopapp.model.Order
import com.example.coffeeshopapp.model.customer.OrderModel
import com.google.firebase.auth.FirebaseAuth

class OrderViewModel {

    private var orderModel = OrderModel()
    private val cartViewModel = CartViewModel.getInstance()
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun submitOrder(
        name: String,
        callback: (String?) -> Unit
    ) {
        val userId = firebaseAuth.currentUser?.uid
        if (userId == null || name.isEmpty() ) {
            callback(null)
            return
        }

        val cartItems = cartViewModel.getCartItems()
        if (cartItems.isEmpty()) {
            callback(null)
            return
        }
        val items = cartItems.map { it.first }
        val itemsMap = items.associateBy { it.productId }
        val order = Order(
            customerId = userId,
            items = itemsMap,
            status = "pending",
        )

        orderModel.createOrder(order) { orderId ->
            if (orderId != null) {
                cartViewModel.clearCart()
                callback(orderId)
            } else {
                callback(null)
            }
        }
    }

    fun getOrder(callback: (List<Order>) -> Unit) {
        val userId = firebaseAuth.currentUser?.uid ?: return
        orderModel.getOrder(userId, callback)
    }
}


