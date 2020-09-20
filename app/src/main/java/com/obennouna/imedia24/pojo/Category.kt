package com.obennouna.imedia24.pojo

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.obennouna.imedia24.utils.CategoryTypeConverter

// We annotate @keep this class to avoid having it obfuscated by ProGuard
@Keep
@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val id: Int,
    val displayName: String?,
    val resultCount : Int,
    val children: ArrayList<Category>
)