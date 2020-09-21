package com.obennouna.imedia24.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.obennouna.imedia24.pojo.Product

@Dao
interface ProductDAO {
    @Query("SELECT * FROM product WHERE categoryId IS :categoryId")
    suspend fun findByCategory(categoryId: Int): List<Product>?

    @Insert
    suspend fun insertAll(products: List<Product>)
}