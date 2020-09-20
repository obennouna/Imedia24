package com.obennouna.imedia24.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.obennouna.imedia24.dao.CategoryDAO
import com.obennouna.imedia24.pojo.Category
import com.obennouna.imedia24.utils.CategoryTypeConverter
import com.obennouna.imedia24.utils.SingletonHolder

@Database(entities = arrayOf(Category::class), version = 2, exportSchema = false)
@TypeConverters(CategoryTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDAO

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext,
            AppDatabase::class.java, "imedia24.db")
            .build()
    })
}
