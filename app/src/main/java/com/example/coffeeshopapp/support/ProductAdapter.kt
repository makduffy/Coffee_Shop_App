package com.example.coffeeshopapp.support

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.ProductLayoutBinding
import com.example.coffeeshopapp.model.Product
import com.example.coffeeshopapp.view.customer.SelectedProductActivity

class ProductAdapter(private var productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ProductLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.productName.text = product.name
            binding.productDescription.text = product.description
            binding.productPrice.text = String.format("£%.2f", product.price)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)

        holder.binding.cardView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SelectedProductActivity::class.java).apply {
                putExtra("product_id", product.id)
            }
            context.startActivity(intent)
        }
    }
    override fun getItemCount() = productList.size

    fun updateData(newProductList: List<Product>) {
        productList = newProductList
        notifyDataSetChanged()
    }
}