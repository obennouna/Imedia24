package com.obennouna.imedia24.ui.categories

import com.obennouna.imedia24.pojo.Category

class CategoryViewModel(val category: Category) {

    fun displaySubCategories(): String {
        return category.children.size.toString()
    }

    fun displayName(): String {
        return category.displayName
    }
}