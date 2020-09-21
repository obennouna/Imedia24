package com.obennouna.imedia24.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.obennouna.imedia24.pojo.Category

@Dao
interface CategoryDAO {
    @Query("SELECT * FROM Category ORDER BY displayName ASC")
    suspend fun getAll(): List<Category>?

    @Insert
    suspend fun insertAll(categories: List<Category>)
}
