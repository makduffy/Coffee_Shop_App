package com.example.coffeeshopapp.model.customer

import android.util.Log
import com.example.coffeeshopapp.model.CartItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CartModel {
    private val database = FirebaseDatabase.getInstance()

    fun getCurrentCustomerId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }
    fun createCart(userId: String, cartItem: CartItem) {
        val databaseReference = database.getReference("Cart/$userId")
        databaseReference.child("userId").setValue(userId)
        val productId = cartItem.productId
        databaseReference.child("products").child(productId).setValue(cartItem)
    }

    fun getCart(userId: String, callback: (List<CartItem>) -> Unit) {
        val productsRef = database.getReference("Cart/$userId/products")
        productsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot.children.mapNotNull { it.getValue(CartItem::class.java) }
                callback(items)
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }
    fun addToCustomerCart(userId: String, cartItem: CartItem) {
        Log.d("CartModel", "Adding item to cart: $cartItem for user $userId")
        val databaseReference = database.getReference("Cart/$userId/products")
        val cartItemKey = databaseReference.push().key
        cartItemKey?.let {
            databaseReference.child(it).setValue(cartItem)
        }
    }

    fun deleteCartItem(userId: String, cartItem: CartItem) {
        val databaseReference = database.getReference("Cart/$userId/${cartItem.productId}")
        databaseReference.removeValue()
    }

    fun cartExists(userId: String, callback: (Boolean) -> Unit) {
        val databaseRef = database.getReference("Cart/$userId")
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                callback(dataSnapshot.exists())
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

}