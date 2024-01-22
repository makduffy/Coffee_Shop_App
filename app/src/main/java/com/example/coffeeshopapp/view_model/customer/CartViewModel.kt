package com.example.coffeeshopapp.view_model.customer

import android.util.Log
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
    private fun createNewCart(userId: String, cartItem: CartItem){
        cartModel.createCart(userId, cartItem)
    }

    fun addToCart(cartItem: CartItem) {
        val userId = cartModel.getCurrentCustomerId()
        if (userId != null) {
            Log.d("CartViewModel", "Adding item to cart: $cartItem for user $userId")
            cartModel.cartExists(userId) { exists ->
                if (exists) {
                    Log.d("CartViewModel", "Cart exists, adding item")
                    cartModel.addToCustomerCart(userId, cartItem)
                } else {
                    Log.d("CartViewModel", "Cart does not exist, creating new cart")
                    createNewCart(userId, cartItem)
                }
            }
        } else {
            Log.d("CartViewModel", "User ID is null, cannot add to cart")
        }
    }

}
