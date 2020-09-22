package com.obennouna.imedia24.viewmodel.cart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obennouna.imedia24.pojo.CartItem
import com.obennouna.imedia24.repository.cart.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartItemViewModel : ViewModel() {

    private val cartRepository = CartRepository()
    private val _cartItems: MutableLiveData<List<CartItem>> = MutableLiveData()

    var cartItems: LiveData<List<CartItem>> = _cartItems

    fun getProductsByCategory(context: Context) {
        _cartItems.apply {
            GlobalScope.launch(Dispatchers.Main) {
                value = context.let { cartRepository.getAllProducts(it) }
            }
        }
    }

    fun removeProductFromCart(context: Context, cartItem: CartItem) {
        _cartItems.apply {
            GlobalScope.launch(Dispatchers.Main) {
                value = context.let { cartRepository.deleteProduct(it, cartItem.product.sku) }
            }
        }
    }
}