package com.example.coffeeshopapp.view_model.customer

import com.example.coffeeshopapp.model.CartItem
import com.example.coffeeshopapp.model.customer.CartModel

class CartViewModel {

    private var cartModel = CartModel()

    fun getCartItems(callback: (List<CartItem>) -> Unit) {
        cartModel.getCurrentCustomerId()?.let { userId ->
            cartModel.getCart(userId, callback)
        }
    }
    fun deleteCartItem(cartItem: CartItem) {
        val userId = cartModel.getCurrentCustomerId()
        if (userId != null) {
            cartModel.deleteCartItem(userId, cartItem)
        }
    }
    fun createNewCart(userId: String, cartItem: CartItem){
        cartModel.createCart(userId, cartItem)
    }

    fun addToCart(cartItem: CartItem){
        val userId = cartModel.getCurrentCustomerId()
        if (userId != null) {
            cartModel.addToCustomerCart(userId, cartItem)
        }
    }
}
