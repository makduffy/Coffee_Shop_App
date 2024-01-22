package com.example.coffeeshopapp.support

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshopapp.databinding.OrderItemLayoutBinding
import com.example.coffeeshopapp.model.Order
import com.example.coffeeshopapp.model.Payment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OrderAdapter(private var orderList: MutableList<Pair<Order, Double>>) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(private val binding: OrderItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(orderWithPrice: Pair<Order, Double>) {
            val (order, totalPrice) = orderWithPrice
            binding.orderStatus.text = "Status: ${order.status}"
            binding.itemsList.text = "Items: ${order.items?.keys?.joinToString(", ")}"
            binding.totalPrice.text = "Total Price: $${totalPrice}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = OrderItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.bind(order)
    }

    override fun getItemCount() = orderList.size

    fun updateData(newOrderList: MutableList<Pair<Order, Double>>) {
        orderList.clear()
        orderList.addAll(newOrderList)
        notifyDataSetChanged()
    }


}
