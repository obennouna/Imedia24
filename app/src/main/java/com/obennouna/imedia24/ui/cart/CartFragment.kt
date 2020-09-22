package com.obennouna.imedia24.ui.cart

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
import com.google.android.material.snackbar.Snackbar
import com.obennouna.imedia24.R
import com.obennouna.imedia24.pojo.CartItem
import com.obennouna.imedia24.ui.productDetail.ProductDetailActivity
import com.obennouna.imedia24.viewmodel.cart.CartItemViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment(), CartAdapter.OnItemClickListener {

    private lateinit var cartViewModel: CartItemViewModel
    private val cartAdapter = CartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartViewModel = ViewModelProvider(this).get(CartItemViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_cart, container, false)
        cartViewModel.cartItems.observe(viewLifecycleOwner, Observer {
            displayProducts(it)
        })
        return root
    }

    private fun displayProducts(cartItems: List<CartItem>) {
        cart_total_price.text = getString(R.string.total_price, calculateTotalPrice(cartItems))
        cartAdapter.setData(cartItems)
        cartAdapter.notifyDataSetChanged()
    }

    private fun calculateTotalPrice(cartItems: List<CartItem>): Float {
        var totalPrice = 0.0f
        for (cartItem in cartItems) {
            totalPrice += cartItem.product.productPrice.price * cartItem.quantity
        }
        return totalPrice
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        cartAdapter.onItemClickListener(this)
        cart_item_rv.setHasFixedSize(true)
        cart_item_rv.layoutManager = LinearLayoutManager(context)
        cart_item_rv.itemAnimator = DefaultItemAnimator()
        cart_item_rv.adapter = cartAdapter

        context?.let { cartViewModel.getProductsByCategory(it) }
    }

    override fun onItemClickListener(cartItem: CartItem) {
        startActivity(context?.let { ProductDetailActivity.navigateTo(cartItem.product, it) })
    }

    override fun onItemDeleted(cartItem: CartItem) {
        Snackbar.make(cart_item_rv, cartItem.product.nameShort + " has been removed from cart", Snackbar.LENGTH_LONG).show()
        context?.let { cartViewModel.removeProductFromCart(it, cartItem) }
    }
}