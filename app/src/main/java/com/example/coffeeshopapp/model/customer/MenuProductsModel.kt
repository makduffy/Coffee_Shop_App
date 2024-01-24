package com.example.coffeeshopapp.model.customer

import com.example.coffeeshopapp.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MenuProductsModel {
    private val database = FirebaseDatabase.getInstance()

    fun getProductsByCategory(category: String, callback: (List<Product>) -> Unit) {
        val productsReference = database.getReference("product")
        productsReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productList = mutableListOf<Product>()
                for (productSnapshot in snapshot.children) {
                    val product = productSnapshot.getValue(Product::class.java)
                    if (product?.category == category) {
                        productList.add(product)
                    }
                }
                callback(productList)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                callback(emptyList())
            }
        })
    }

    fun getProductById(productId: String, callback: (Product?) -> Unit) {
        val productReference = database.getReference("product").child(productId)
        productReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val product = snapshot.getValue(Product::class.java)
                callback(product)
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }
}