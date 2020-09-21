package com.obennouna.imedia24.viewmodel.category

import android.content.Context
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
    private val _categories: MutableLiveData<List<CategoryViewModel>> = MutableLiveData()

    var categories: LiveData<List<CategoryViewModel>> = _categories

    fun getCategories(context: Context) {
        _categories.apply {
            GlobalScope.launch(Dispatchers.Main) {
                val listCategories = context.let { categoryRepository.getCategories(it) }
                value = transformToViewModel(listCategories)
            }
        }
    }

    private fun transformToViewModel(listCategories: List<Category>): List<CategoryViewModel>? {
        val toReturn: ArrayList<CategoryViewModel> = ArrayList()
        for (category in listCategories) {
            toReturn.add(
                CategoryViewModel(
                    category
                )
            )
        }
        return toReturn
    }
}