package com.obennouna.imedia24.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.obennouna.imedia24.R
import com.obennouna.imedia24.pojo.Category
import kotlinx.android.synthetic.main.fragment_gallery.*

class CategoriesFragment : Fragment() {

    private lateinit var galleryViewModel: CategoriesViewModel
    private val categoriesAdapter = CategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        galleryViewModel.categories.observe(viewLifecycleOwner, Observer {
            displayCategories(it)
            progress_circular.visibility = View.GONE
        })
        return root
    }

    private fun displayCategories(categories: List<Category>) {
        categoriesAdapter.setData(categories)
        categoriesAdapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categories_rv.setHasFixedSize(true)
        categories_rv.layoutManager = LinearLayoutManager(context)
        categories_rv.itemAnimator = DefaultItemAnimator()
        categories_rv.adapter = categoriesAdapter

        galleryViewModel.getCategories(requireContext())
    }
}