package com.obennouna.imedia24.repository

import android.content.Context
import com.obennouna.imedia24.database.AppDatabase
import com.obennouna.imedia24.network.IMedia24APIAdapter
import com.obennouna.imedia24.pojo.Product
import java.util.ArrayList

class ProductRepository {

    /**
     * The reasoning behind this is that Categories aren't something that will change in the future (in general).
     * If needed we could implement it in a better way to check if there are any new ones instead of always using the local data if available.
     */
    suspend fun getProductsByCategory(context: Context, categoryId: Int): List<Product> {
        val productDao = AppDatabase.getInstance(context).productDao()
        val result = productDao.findByCategory(categoryId)
        if (!result.isNullOrEmpty()) {
            return result
        }

        try {
            val response = IMedia24APIAdapter.apiClient.getProductsByCategory(categoryId)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                val toReturn = data.productResults
                addCategoryId(toReturn, categoryId)
                productDao.insertAll(toReturn)
                return toReturn
            }
        } catch (e: Exception) {
            return emptyList()
        }
        return emptyList()
    }

    private fun addCategoryId(products: ArrayList<Product>, categoryId: Int) {
        for (product in products) {
            product.categoryId = categoryId
        }
    }
}