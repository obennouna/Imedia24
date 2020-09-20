package com.obennouna.imedia24.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.obennouna.imedia24.pojo.Category

@Dao
interface CategoryDAO {
    @Query("SELECT * FROM Category ORDER BY displayName ASC")
    suspend fun getAll(): List<Category>?

    @Query("SELECT * FROM category WHERE displayName LIKE :categoryName LIMIT 1")
    suspend fun findByName(categoryName: String): Category?

    @Insert
    suspend fun insertAll(categories: List<Category>)

    @Delete
    suspend fun delete(category: Category)

    @Query("DELETE FROM category")
    suspend fun nukeTable()
}
