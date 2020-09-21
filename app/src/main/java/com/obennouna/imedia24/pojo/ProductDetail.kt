package com.obennouna.imedia24.pojo

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Entity
@Parcelize
data class ProductDetail(
    @PrimaryKey val sku: Int,
    var title: String,
    val nameShort: String,
    val longDescription: String,
    val averageStars: Float,
    val productPrice: Price,
    val imageUris: ArrayList<String>,
    val brandNameShort: String,
    val brandNameLong: String,
    val additionalInformation: String,
    val legalText: String,
    val deliveryText: String
): Parcelable
