package com.obennouna.imedia24.ui.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.obennouna.imedia24.R
import com.obennouna.imedia24.databinding.ItemCartBinding
import com.obennouna.imedia24.pojo.CartItem

class CartAdapter: RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var cartItems: ArrayList<CartItem> = ArrayList()
    private var mListener: OnItemClickListener? = null
    
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartAdapter.ViewHolder {
        val binding: ItemCartBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_cart,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }

    fun onItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    fun setData(newProducts: List<CartItem>) {
        cartItems.clear()
        cartItems.addAll(newProducts)
    }
    inner class ViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(data: CartItem) {
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
                .into(binding.root.findViewById(R.id.cart_item_placeholder))
            binding.root.findViewById<ImageView>(R.id.cart_item_remove_item)
                .setOnClickListener(this)
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            if (v is ImageView) {
                mListener?.onItemDeleted(binding.model!!)
            } else {
                mListener?.onItemClickListener(binding.model!!)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(cartItem: CartItem)
        fun onItemDeleted(cartItem: CartItem)
    }
}