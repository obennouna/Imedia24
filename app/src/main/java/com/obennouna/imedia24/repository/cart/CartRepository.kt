package com.obennouna.imedia24.repository.cart

import android.content.Context
import com.obennouna.imedia24.dao.CartDAO
import com.obennouna.imedia24.pojo.CartItem
import com.obennouna.imedia24.pojo.Product

class CartRepository {
    private val cartDAO = CartDAO()

    fun getAllProducts(context: Context): List<CartItem> {
        return cartDAO.getAllProducts(context)
    }

    fun deleteProduct(context: Context, productSKU: Int): List<CartItem> {
        cartDAO.deleteProduct(context, productSKU)
        return getAllProducts(context)
    }

    fun insertProduct(context: Context, product: Product) {
        return cartDAO.insertProduct(context, product)
    }
}