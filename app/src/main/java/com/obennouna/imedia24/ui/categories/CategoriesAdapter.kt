package com.obennouna.imedia24.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.obennouna.imedia24.R
import com.obennouna.imedia24.databinding.ItemCategoryBinding
import com.obennouna.imedia24.viewmodel.category.CategoryViewModel

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    private var categories: ArrayList<CategoryViewModel> = ArrayList()
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_category,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    fun onItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    fun setData(newCategories: List<CategoryViewModel>) {
        categories.clear()
        categories.addAll(newCategories)
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(data: CategoryViewModel) {
            binding.model = data
            binding.root.setOnClickListener(this)
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            mListener?.onItemClickListener(binding.model!!)
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(category: CategoryViewModel)
    }
}