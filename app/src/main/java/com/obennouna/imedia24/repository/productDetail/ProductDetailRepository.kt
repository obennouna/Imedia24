package com.obennouna.imedia24.repository.productDetail

import android.content.Context
import com.obennouna.imedia24.database.AppDatabase
import com.obennouna.imedia24.network.IMedia24APIAdapter
import com.obennouna.imedia24.pojo.ProductDetail

class ProductDetailRepository {
    /**
     * The reasoning behind this is that Categories aren't something that will change in the future (in general).
     * If needed we could implement it in a better way to check if there are any new ones instead of always using the local data if available.
     */
    suspend fun getProductDetailBySKU(context: Context, sku: Int): ProductDetail? {
        val productDetailDao = AppDatabase.getInstance(context).productDetailDao()
        val result = productDetailDao.findBySKU(sku)
        if (result != null) {
            return result
        }

        try {
            val response = IMedia24APIAdapter.apiClient.getProductByCategory(sku)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                productDetailDao.insert(data)
                return data
            }
        } catch (e: Exception) {
            return null
        }
        return null
    }
}