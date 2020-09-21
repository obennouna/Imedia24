package com.obennouna.imedia24.pojo

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ResponseProduct(
    val topShop: String,
    val resultCount: Int,
    val productResults: ArrayList<Product>
) : Parcelable
