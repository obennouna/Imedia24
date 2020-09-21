package com.obennouna.imedia24.pojo

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.obennouna.imedia24.utils.CategoryTypeConverter
import kotlinx.android.parcel.Parcelize

// We annotate @keep this class to avoid having it obfuscated by ProGuard
@Keep
@Entity
@Parcelize
data class Category(
    @PrimaryKey val categoryId: Int,
    val displayName: String,
    val resultCount : Int,
    val children: ArrayList<Category>
) : Parcelable