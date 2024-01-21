package com.example.coffeeshopapp.support

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductLayoutBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)

        holder.binding.cardView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SelectedProductActivity::class.java).apply {
                putExtra("productId", product.id)
            }
            context.startActivity(intent)
        }
    }
    override fun getItemCount() = productList.size

    fun updateProducts(newProductList: List<Product>) {
        productList = newProductList
        notifyDataSetChanged()
    }
}