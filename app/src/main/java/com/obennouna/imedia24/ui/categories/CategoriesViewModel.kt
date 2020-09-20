package com.obennouna.imedia24.ui.categories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obennouna.imedia24.pojo.Category
import com.obennouna.imedia24.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoriesViewModel : ViewModel() {

    private val categoryRepository = CategoryRepository()
    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()

    var categories: LiveData<List<Category>> = _categories

    fun getCategories(context: Context) {
        _categories.apply {
            GlobalScope.launch(Dispatchers.Main) {
                value = context.let { categoryRepository.getCategories(it) }
                Log.d(CategoriesViewModel::class.simpleName, "Result size : " + value?.size)
                Log.d(CategoriesViewModel::class.simpleName, "Result[0].categoryId : " + value?.get(0)?.categoryId)
            }
        }
    }

    fun setCategories(categories: List<Category>) {
        _categories.apply {
            value = categories
            Log.d(CategoriesViewModel::class.simpleName, "Result[0].categoryId : " + value?.get(0)?.categoryId)
        }
    }
}