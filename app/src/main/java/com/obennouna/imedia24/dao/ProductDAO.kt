package com.obennouna.imedia24.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.obennouna.imedia24.pojo.Product

@Dao
interface ProductDAO {
    @Query("SELECT * FROM product ORDER BY nameShort ASC")
    suspend fun getAll(): List<Product>?

    @Query("SELECT * FROM product WHERE nameShort LIKE :productName LIMIT 1")
    suspend fun findByName(productName: String): Product?

    @Insert
    suspend fun insertAll(products: List<Product>)
}