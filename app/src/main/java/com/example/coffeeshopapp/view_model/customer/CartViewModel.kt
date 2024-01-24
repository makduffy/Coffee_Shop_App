package com.example.coffeeshopapp.view_model.customer

import com.example.coffeeshopapp.model.CartItem
import com.example.coffeeshopapp.model.customer.MenuProductsModel

class CartViewModel private constructor() {

    private val cartItems = mutableListOf<CartItem>()
    private val menuProductsModel = MenuProductsModel()
    companion object {
        private var INSTANCE: CartViewModel? = null

        fun getInstance(): CartViewModel {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: CartViewModel().also { INSTANCE = it }
            }
        }
    }

    @Synchronized
    fun getCartItems(): List<Pair<CartItem, Int>> {
        return cartItems.map { Pair(it, it.quantity) }.toList()
    }
    @Synchronized
    fun addToCart(cartItem: CartItem) {
        cartItems.add(cartItem)
    }
    @Synchronized
    fun deleteCartItem(cartItem: CartItem) {
        cartItems.remove(cartItem)
    }
    @Synchronized
    fun clearCart() {
        cartItems.clear()
    }
    @Synchronized
    fun getTotalPrice(callback: (Double) -> Unit) {
        var totalPrice = 0.0
        var itemsProcessed = 0

        if (cartItems.isEmpty()) {
            callback(totalPrice)
            return
        }

        cartItems.forEach { cartItem ->
            menuProductsModel.getProductById(cartItem.productId) { product ->
                product?.let {
                    totalPrice += it.price * cartItem.quantity
                }
                itemsProcessed++
                if (itemsProcessed == cartItems.size) {
                    callback(totalPrice)
                }
            }
        }
    }
}

