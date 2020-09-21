package com.obennouna.imedia24.ui.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.obennouna.imedia24.R
import com.obennouna.imedia24.databinding.ProductItemBinding
import com.obennouna.imedia24.viewmodel.product.ProductViewModel

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var products: ArrayList<ProductViewModel> = ArrayList()
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsAdapter.ViewHolder {
        val binding: ProductItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.product_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun onItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    fun setData(newProducts: List<ProductViewModel>) {
        products.clear()
        products.addAll(newProducts)
    }

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(data: ProductViewModel) {
            binding.model = data
            binding.root.setOnClickListener(this)
            Glide
                .with(binding.root.context)
                .load(data.buildImageUrl())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.baseline_attach_money_black_24)
                        .error(R.drawable.baseline_attach_money_black_24).dontTransform()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(binding.root.findViewById(R.id.product_placeholder))
            binding.root.findViewById<ImageView>(R.id.product_add_cart)
                .setOnClickListener {
                    Snackbar.make(
                        it,
                        "Yet to be implemented",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            mListener?.onItemClickListener(binding.model!!)
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(product: ProductViewModel)
    }
}