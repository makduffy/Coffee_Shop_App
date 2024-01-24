package com.example.coffeeshopapp.model
data class Customer(
    var authId: String? = null,
    var name: String? = null,
    var password: String? = null,
    var email: String? = null,
    var phoneNo: String? = null,
    var address: String? = null
){
data class Admin(
    val id: String,
    val name: String,
    val email: String,
    val adminLevel: Int
)}