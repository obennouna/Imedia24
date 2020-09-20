package com.obennouna.imedia24.repository

import android.content.Context
import android.util.Log
import com.obennouna.imedia24.database.AppDatabase
import com.obennouna.imedia24.network.IMedia24APIAdapter
import com.obennouna.imedia24.pojo.Category

class CategoryRepository {

    /**
     * The reasoning behind this is that Categories aren't something that will change in the future (in general).
     * If needed we could implement it in a better way to check if there are any new ones instead of always using the local data if available.
     */
    suspend fun getCategories(context: Context): List<Category> {
        val categoryDAO = AppDatabase.getInstance(context).categoryDao()
        val result = categoryDAO.getAll()
        if (!result.isNullOrEmpty()) {
            Log.d(CategoryRepository::class.simpleName, "DAO Children Size : " + result.size)
            return result
        }

        try {
            val response = IMedia24APIAdapter.apiClient.getTreeCategory()
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!
                val toReturn = data.children
                categoryDAO.insertAll(toReturn)
                Log.d(CategoryRepository::class.simpleName, "REST Children Size : " + toReturn.size)
                return toReturn
            }
        } catch (e: Exception) {
            return emptyList()
        }
        return emptyList()
    }
}