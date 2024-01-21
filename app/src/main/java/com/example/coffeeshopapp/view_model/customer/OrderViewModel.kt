package com.example.coffeeshopapp.view_model.customer

import com.example.coffeeshopapp.model.Customer
import com.example.coffeeshopapp.model.Order
import com.example.coffeeshopapp.model.customer.CartModel
import com.example.coffeeshopapp.model.customer.OrderModel

class OrderViewModel {

    private var orderModel = OrderModel()
    private var cartModel = CartModel()

    fun convertCartToOrder(userId: String, customerName: String, address: String, paymentMethod: String, callback: (Boolean) -> Unit) {
        if (customerName.isEmpty() || address.isEmpty() || paymentMethod.isEmpty()) {
            callback(false)
            return
        }

        cartModel.getCart(userId) { cartItems ->
            val order = Order(
                items = cartItems,
                customerDetails = Customer(name = customerName, address = address),
                paymentMethod = paymentMethod
            )
            orderModel.createOrder(order) { success ->
                if (success) {
                    orderModel.clearCart(userId) { cartCleared ->
                        callback(cartCleared)
                    }
                } else {
                    callback(false)
                }
            }
        }
    }
}