package com.example.coffeeshopapp.support

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshopapp.databinding.CartItemLayoutBinding
import com.example.coffeeshopapp.model.CartItem

class CartItemAdapter(private var cartItemList: MutableList<CartItem>, private val onDeleteAction: (CartItem) -> Unit) : RecyclerView.Adapter<CartItemAdapter.CartViewHolder>() {

    class CartViewHolder(private val binding: CartItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem, deleteAction: (CartItem) -> Unit) {
            binding.cartItemName.text = cartItem.productId
            binding.cartItemQuantity.text = "Qty: ${cartItem.quantity}"
            binding.btnDeleteCartItem.setOnClickListener {
                deleteAction(cartItem)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItemList[position]
        holder.bind(cartItem, onDeleteAction)
    }

    override fun getItemCount() = cartItemList.size

    fun updateData(newCartItemList: MutableList<CartItem>) {
        cartItemList = newCartItemList
        notifyDataSetChanged()
    }
    fun deleteItem(cartItem: CartItem) {
        val position = cartItemList.indexOf(cartItem)
        if (position >= 0) {
            cartItemList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItemAtPosition(position: Int): CartItem {
        return cartItemList[position]
    }
}

