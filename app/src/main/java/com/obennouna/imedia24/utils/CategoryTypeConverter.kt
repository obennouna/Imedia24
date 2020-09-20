package com.obennouna.imedia24.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.obennouna.imedia24.pojo.Category
import java.lang.reflect.Type

class CategoryTypeConverter {
    @TypeConverter
    fun fromString(value: String?): ArrayList<Category?>? {
        val listType: Type = object : TypeToken<ArrayList<Category?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCategories(list: ArrayList<Category?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}