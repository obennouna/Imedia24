package com.obennouna.imedia24.pojo

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Entity
@Parcelize
data class Price(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val price: Float,
    val referencePrice: Float
): Parcelable