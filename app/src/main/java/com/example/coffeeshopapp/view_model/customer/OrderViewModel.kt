package com.example.coffeeshopapp.view_model.customer

import android.util.Log
import com.example.coffeeshopapp.model.Order
import com.example.coffeeshopapp.model.customer.CartModel
import com.example.coffeeshopapp.model.customer.OrderModel


class OrderViewModel {

    private var orderModel = OrderModel()
    private var cartModel = CartModel()

    fun convertCartToOrder(
        userId: String, customerName: String,
        address: String, paymentMethod: String, callback: (Boolean) -> Unit) {
        Log.d("OrderViewModel", "Starting to convert cart to order for user $userId")

        if (customerName.isEmpty() || address.isEmpty() || paymentMethod.isEmpty()) {
            Log.d("OrderViewModel", "Order conversion failed: Missing required fields")
            callback(false)
            return
        }

        cartModel.getCart(userId) { cartItems ->
            Log.d("OrderViewModel", "Retrieved cart items for order conversion: $cartItems")
            val itemsMap = cartItems.associateBy { it.productId }
            val order = Order(
                customerId = userId,
                items = itemsMap,
                status = "pending",
                paymentMethod = paymentMethod
            )
            orderModel.createOrder(order) { success ->
                if (success) {
                    Log.d("OrderViewModel", "Order created successfully")
                    orderModel.clearCart(userId) { cartCleared ->
                        Log.d("OrderViewModel", "Cart cleared after order creation: $cartCleared")
                        callback(cartCleared)
                        callback(cartCleared)
                    }
                } else {
                    Log.d("OrderViewModel", "Failed to create order")
                    callback(false)
                }
            }
        }
    }
}