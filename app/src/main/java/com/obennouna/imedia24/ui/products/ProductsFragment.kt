package com.obennouna.imedia24.ui.products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.obennouna.imedia24.R
import com.obennouna.imedia24.pojo.Category
import kotlinx.android.synthetic.main.fragment_categories.*

class ProductsFragment: Fragment() {

    companion object {
        private const val CATEGORY = "CATEGORY"
        fun navigateTo(category: Category): ProductsFragment {
            val fragment = ProductsFragment()
            val bundle = Bundle()

            bundle.putParcelable(CATEGORY, category)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var productViewModel: ProductsViewModel
//    private val categoriesAdapter = CategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_categories, container, false)
        productViewModel.categories.observe(viewLifecycleOwner, Observer {
            displayCategories(it)
            progress_circular.visibility = View.GONE
        })
        return root
    }

    private fun displayCategories(categories: List<Category>) {
//        categoriesAdapter.setData(categories)
//        categoriesAdapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categories_rv.setHasFixedSize(true)
        categories_rv.layoutManager = LinearLayoutManager(context)
        categories_rv.itemAnimator = DefaultItemAnimator()
        categories_rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//        categories_rv.adapter = categoriesAdapter
//        categoriesAdapter.onItemClickListener(this)

        productViewModel.getCategories(requireContext())
    }

//    override fun onItemClickListener(category: Category) {
//        TODO("Display Product + sub categories")
//    }
}