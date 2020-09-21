package com.obennouna.imedia24.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.obennouna.imedia24.pojo.Category
import com.obennouna.imedia24.pojo.Price
import java.lang.reflect.Type

class CategoryTypeConverter {

    @TypeConverter
    fun fromStringCategories(value: String?): ArrayList<Category?>? {
        val listType: Type = object : TypeToken<ArrayList<Category?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCategories(list: ArrayList<Category?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringPrice(value: String?): Price? {
       return Gson().fromJson(value, Price::class.java)
    }

    @TypeConverter
    fun fromPrice(price: Price?): String? {
        return Gson().toJson(price)
    }

    @TypeConverter
    fun fromStringUri(value: String?): ArrayList<String>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
       return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromUri(uri: List<String>?): String? {
        return Gson().toJson(uri)
    }
}