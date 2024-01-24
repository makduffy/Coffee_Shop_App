package com.example.coffeeshopapp.view.customer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshopapp.databinding.ActivityCustomerAccountBinding
import com.example.coffeeshopapp.databinding.OrderItemLayoutBinding
import com.example.coffeeshopapp.model.Customer
import com.example.coffeeshopapp.model.Order
import com.example.coffeeshopapp.model.Payment
import com.example.coffeeshopapp.support.OrderAdapter
import com.example.coffeeshopapp.view_model.customer.OrderViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CustomerAccount : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerAccountBinding
    private lateinit var orderAdapter: OrderAdapter
    private var orderViewModel = OrderViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchCustomerDetails()
        fetchOrderHistory()
        setUpRecyclerView()
        setUpListeners()

    }
    private fun setUpListeners() {
        binding.btnSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnCart.setOnClickListener {
            startActivity(Intent(this, CustomerViewCart::class.java))
        }
        binding.btnMenu.setOnClickListener {
            startActivity(Intent(this, CustomerMenu::class.java))
        }
        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, CustomerAccount::class.java))
        }
    }
    private fun fetchCustomerDetails() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val databaseReference = FirebaseDatabase.getInstance().getReference("Customer")

        databaseReference.child(userId).get().addOnSuccessListener { dataSnapshot ->
            val customerAccount = dataSnapshot.getValue(Customer::class.java)
            customerAccount?.let {
                updateUIWithCustomerDetails(it)
            }
        }.addOnFailureListener {
        }
    }
    private fun updateUIWithCustomerDetails(customer: Customer) {
        binding.customerName.text = customer.name
        binding.customerEmail.text = customer.email
        binding.customerPhone.text = customer.phoneNo
    }
    private fun updateUIWithOrderHistory(ordersWithPrice: MutableList<Pair<Order, Double>>) {
        orderAdapter.updateData(ordersWithPrice)
    }
    private fun setUpRecyclerView() {
        orderAdapter = OrderAdapter(mutableListOf())
        binding.productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CustomerAccount)
            adapter = orderAdapter
        }
    }

    private fun fetchOrderHistory() {
        orderViewModel.getOrder { orders ->
            (orders.isEmpty())
            val fetchedOrdersWithPrice = mutableListOf<Pair<Order, Double>>()
            var processedOrders = 0

            orders.forEach { order ->
                fetchTotalPrice(order.orderId) { totalPrice ->
                    fetchedOrdersWithPrice.add(Pair(order, totalPrice))

                    processedOrders++
                    if (processedOrders == orders.size) {
                        updateUIWithOrderHistory(fetchedOrdersWithPrice)
                    }
                }
            }
        }
    }
    private fun fetchTotalPrice(orderId: String?, callback: (Double) -> Unit) {
        if (orderId == null) {
            callback(0.0)
            return
        }
        val paymentsRef = FirebaseDatabase.getInstance().getReference("payments").child(orderId)
        paymentsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val payment = snapshot.getValue(Payment::class.java)
                callback(payment?.totalPrice ?: 0.0)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                callback(0.0)
            }
        })
    }

}

