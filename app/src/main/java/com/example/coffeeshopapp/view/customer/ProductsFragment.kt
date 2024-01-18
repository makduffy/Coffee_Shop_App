package com.example.coffeeshopapp.view.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.adapter.ProductAdapter
import com.example.coffeeshopapp.view_model.customer.ProductViewModel

class ProductsFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        argument? property inherited from fragment class, let {} block only execute if arguments is NOT NULL
         */
        arguments?.let {
            category = it.getString("category")
        }
    }

    override fun onCreateView(
        //Inflate the fragment onto the view
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_display_products, container, false)
    }
    /*
    Initialise the view hierarchy by calling the methods
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        setupRecyclerView(view)
        observeViewModel()
    }
    /*
    Set up Recycler view, position the item views within RecyclerView, bind data to views from the custom adapter
     */
    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.productsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        productAdapter = ProductAdapter(listOf()) //
        recyclerView.adapter = productAdapter
    }

    private fun observeViewModel() {
        productViewModel.products.observe(viewLifecycleOwner) { products ->

            val filteredProducts = products.filter { it.category == category }
            productAdapter.updateData(filteredProducts)
        }
        productViewModel.loadProductsByCategory(category)
    }

    companion object {
        fun newInstance(category: String) = ProductsFragment().apply {
            arguments = Bundle().apply {
                putString("category", category)
            }
        }
    }
}