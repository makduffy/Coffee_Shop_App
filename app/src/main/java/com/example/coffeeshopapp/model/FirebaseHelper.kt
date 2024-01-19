package com.example.coffeeshopapp.model

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseHelper {

    private val database = FirebaseDatabase.getInstance()

    fun getProducts(callback: (List<Product>) -> Unit) {
        val productsRef = database.getReference("product")
        productsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = mutableListOf<Product>()
                for (productSnapshot in snapshot.children) {
                    val product = productSnapshot.getValue(Product::class.java)
                    product?.let { productList.add(it) }
                }
                callback(productList)
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    fun getProductsByCategory(category: String?, callback: (List<Product>) -> Unit) {
        val productsRef = database.getReference("product")
        productsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = mutableListOf<Product>()
                for (productSnapshot in snapshot.children) {
                    val product = productSnapshot.getValue(Product::class.java)
                    if (product?.category == category) {
                        if (product != null) {
                            productList.add(product)
                        }
                    }
                }
                callback(productList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getProductById(productId: String, callback: (Product?) -> Unit) {
        val productRef = database.getReference("product").child(productId)
        productRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val product = snapshot.getValue(Product::class.java)
                callback(product)
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

}