package com.obennouna.imedia24.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.obennouna.imedia24.dao.CategoryDAO
import com.obennouna.imedia24.dao.ProductDAO
import com.obennouna.imedia24.dao.ProductDetailDAO
import com.obennouna.imedia24.pojo.Category
import com.obennouna.imedia24.pojo.Product
import com.obennouna.imedia24.pojo.ProductDetail
import com.obennouna.imedia24.utils.CategoryTypeConverter
import com.obennouna.imedia24.utils.MigrationDB
import com.obennouna.imedia24.utils.SingletonHolder

// Version 1 : Categories
// Version 2 : Categories + Products (+Price)
// Version 3 : Product Detail
@Database(entities = [Category::class, Product::class, ProductDetail::class], version = 3, exportSchema = true)
@TypeConverters(CategoryTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDAO
    abstract fun productDao(): ProductDAO
    abstract fun productDetailDao(): ProductDetailDAO

    companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext,
            AppDatabase::class.java, "imedia24.db")
            .addMigrations(MigrationDB.MIGRATION_1_2)
            .addMigrations(MigrationDB.MIGRATION_2_3)
            .build()
    })
}
