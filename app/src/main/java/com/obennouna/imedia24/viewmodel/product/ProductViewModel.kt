package com.obennouna.imedia24.viewmodel.product

import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.View
import com.obennouna.imedia24.pojo.Product

class ProductViewModel(val product: Product) {

    fun displayName(): String {
        return product.nameShort
    }

    fun displayPrice(): String {
        return product.productPrice.price.toString() + "€"
    }

    fun displayReferencePrice(): SpannableString {
        val content = product.productPrice.referencePrice.toString() + "€"
        val spannableString = SpannableString(content)
        spannableString.setSpan(StrikethroughSpan(),0,content.length,0)
        return spannableString
    }

    fun shouldDisplayReferencePrice(): Int {
        if (product.productPrice.referencePrice != product.productPrice.price) {
            return View.VISIBLE
        }
        return View.GONE
    }

    fun getRating(): Float {
        return product.averageStars.toFloat()
    }

    fun buildImageUrl(): String {
        if (!product.imageUris.isNullOrEmpty()) {
            return "https://pic.hse24-dach.net/media/de/products/" + product.imageUris[0] + "pics480.jpg"
        }
        return "https://waterfountain.no/wp-content/uploads/2019/11/placeholder.png"
    }
}