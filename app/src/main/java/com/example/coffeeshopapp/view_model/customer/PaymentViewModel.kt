package com.example.coffeeshopapp.view_model.customer

import com.example.coffeeshopapp.model.Payment
import com.example.coffeeshopapp.model.customer.PaymentModel

class PaymentViewModel {

    private val paymentModel = PaymentModel()
    fun submitPayment(orderId: String, totalPrice: Double, paymentType: String, callback: (Boolean) -> Unit) {
        val payment = Payment(orderId, totalPrice, paymentType)
        paymentModel.submitPayment(payment, callback)
    }
}