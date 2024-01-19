package com.example.coffeeshopapp.view.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshopapp.databinding.FragmentDisplayProductsBinding
import com.example.coffeeshopapp.support.ProductAdapter
import com.example.coffeeshopapp.view_model.customer.ProductViewModel

class ProductsFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter
    private var category: String? = null
    private var _binding: FragmentDisplayProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            category = it.getString("category")
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDisplayProductsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        setupRecyclerView()
        observeViewModel()
    }
    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(listOf())
        binding.productsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }
    }
    private fun observeViewModel() {
        productViewModel.products.observe(viewLifecycleOwner) { products ->
            val filteredProducts = products.filter { it.category == category }
            productAdapter.updateData(filteredProducts)
        }
        productViewModel.loadProductsByCategory(category)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        fun newInstance(category: String) = ProductsFragment().apply {
            arguments = Bundle().apply {
                putString("category", category)
            }
        }
    }
}