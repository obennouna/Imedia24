package com.obennouna.imedia24.ui.categories

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
import com.obennouna.imedia24.ui.products.ProductsActivity
import com.obennouna.imedia24.viewmodel.category.CategoriesViewModel
import com.obennouna.imedia24.viewmodel.category.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_categories.*

class CategoriesFragment : Fragment(), CategoriesAdapter.OnItemClickListener {

    private lateinit var categoryViewModel: CategoriesViewModel
    private val categoriesAdapter = CategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categoryViewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_categories, container, false)
        categoryViewModel.categories.observe(viewLifecycleOwner, Observer {
            displayCategories(it)
            progress_circular.visibility = View.GONE
        })
        return root
    }

    private fun displayCategories(categories: List<CategoryViewModel>) {
        categoriesAdapter.setData(categories)
        categoriesAdapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categories_rv.setHasFixedSize(true)
        categories_rv.layoutManager = LinearLayoutManager(context)
        categories_rv.itemAnimator = DefaultItemAnimator()
        categories_rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        categories_rv.adapter = categoriesAdapter
        categoriesAdapter.onItemClickListener(this)

        categoryViewModel.getCategories(requireContext())
    }

    override fun onItemClickListener(category: CategoryViewModel) {
        startActivity(context?.let { ProductsActivity.navigateTo(category.category, it) })
    }
}