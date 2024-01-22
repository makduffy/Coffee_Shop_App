package com.example.coffeeshopapp.view_model.customer

import com.example.coffeeshopapp.model.Product
import com.example.coffeeshopapp.model.customer.MenuProductsModel
class MenuProductsViewModel {

    private val menuModel = MenuProductsModel()
    private var productsCallback: ((List<Product>) -> Unit)? = null
    fun getProductsByCategory(category: String, callback: (List<Product>) -> Unit) {
        menuModel.getProductsByCategory(category, callback)
    }
    fun getProductById(productId: String?, callback: (Product?) -> Unit) {
        productId?.let {
            menuModel.getProductById(it) { product ->
                callback(product)
            }
        } ?: callback(null)
    }
}

