package com.example.coffeeshopapp.model
data class Customer(
    var firstName: String? = null,
    var lastName: String? = null,
    var userName: String? = null,
    var password: String? = null,
    var email: String? = null,
    var phoneNo: String? = null
){
    fun fullName(): String = "$firstName $lastName"
}

data class Admin(
    val id: String,
    val name: String,
    val email: String,
    val adminLevel: Int
)