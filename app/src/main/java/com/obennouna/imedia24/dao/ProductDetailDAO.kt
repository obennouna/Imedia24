package com.obennouna.imedia24.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.obennouna.imedia24.pojo.ProductDetail

@Dao
interface ProductDetailDAO {
    @Query("SELECT * FROM productdetail WHERE sku IS :sku LIMIT 1")
    suspend fun findBySKU(sku: Int): ProductDetail?

    @Insert
    suspend fun insert(productDetail: ProductDetail)
}