package com.obennouna.imedia24.pojo

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Entity
@Parcelize
data class Product(
    @PrimaryKey val sku: Int,
    var categoryId: Int,
    val nameShort: String,
    val averageStars: Int,
    val productPrice: Price,
    val imageUris: ArrayList<String>
) : Parcelable
