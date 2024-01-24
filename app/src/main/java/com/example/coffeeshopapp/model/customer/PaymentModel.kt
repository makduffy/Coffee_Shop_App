package com.example.coffeeshopapp.model.customer

import com.example.coffeeshopapp.model.Payment
import com.google.firebase.database.FirebaseDatabase

class PaymentModel {

    private val database = FirebaseDatabase.getInstance()
    fun submitPayment(payment: Payment, callback: (Boolean) -> Unit) {
        val paymentRef = database.getReference("payments")
        val paymentId = paymentRef.push().key

        paymentId?.let {
            paymentRef.child(it).setValue(payment)
                .addOnSuccessListener {
                    callback(true)
                }
                .addOnFailureListener {
                    callback(false)
                }
        } ?: callback(false)
    }
}