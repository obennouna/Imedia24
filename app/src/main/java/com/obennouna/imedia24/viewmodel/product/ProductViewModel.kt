package com.obennouna.imedia24.viewmodel.product

import com.obennouna.imedia24.pojo.Product

class ProductViewModel(val product: Product) {

    fun displayName(): String {
        if (product.nameShort.length >= 20) {
            return product.nameShort.substring(0, 19).plus("...")
        }
        return product.nameShort
    }

    fun displayPrice(): String {
        return product.productPrice.price.toString() + "€"
    }
}