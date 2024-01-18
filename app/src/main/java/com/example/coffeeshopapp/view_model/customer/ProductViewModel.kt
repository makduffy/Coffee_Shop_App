package com.example.coffeeshopapp.view_model.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffeeshopapp.model.FirebaseHelper
import com.example.coffeeshopapp.model.Product

class ProductViewModel : ViewModel() {

    private val firebaseHelper = FirebaseHelper()
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    fun loadProducts() {
        firebaseHelper.getProducts { productList ->
            _products.postValue(productList)
        }
    }

    fun loadProductsByCategory(category: String?){
        firebaseHelper.getProductsByCategory(category) { productList ->
            _products.postValue(productList)}
    }


}

