package com.obennouna.imedia24.dao

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.obennouna.imedia24.pojo.CartItem
import com.obennouna.imedia24.pojo.Product

class CartDAO {
    private val _sharePreferences = "IMEDIA24_PREFS"

    private fun getSharedPreferences(context: Context): SharedPreferences? {
        return context.getSharedPreferences(_sharePreferences, Context.MODE_PRIVATE)
    }

    private fun getProductBySku(context: Context, sku: Int): CartItem? {
        val cartItem = getSharedPreferences(context)!!.getString(sku.toString(), "")
        if (cartItem.isNullOrEmpty()) {
            return null
        }
        return Gson().fromJson(cartItem, CartItem::class.java)
    }

    fun getAllProducts(context: Context): List<CartItem> {
        val result = ArrayList<CartItem>()
        val allEntries = getSharedPreferences(context)!!.all
        for (entry in allEntries.entries) {
            val productToAdd = Gson().fromJson(entry.value as String, CartItem::class.java)
            result.add(productToAdd)
        }
        return result
    }

    fun deleteProduct(context: Context, productSKU: Int) {
        val editor = getSharedPreferences(context)!!.edit()
        editor.remove(productSKU.toString())
        editor.apply()
    }

    fun insertProduct(context: Context, product: Product) {
        val cartItemToJSON: String
        val editor = getSharedPreferences(context)!!.edit()
        val existingProduct = getProductBySku(context, product.sku)
        cartItemToJSON = if (existingProduct != null) {
            existingProduct.quantity++
            Gson().toJson(existingProduct)
        } else {
            Gson().toJson(CartItem(product, 1))
        }
        editor.putString(product.sku.toString(), cartItemToJSON)
        editor.apply()
    }
}