package com.obennouna.imedia24.pojo

data class CartItem(
    val product: Product,
    var quantity: Int
) {
    fun buildImageUrl(): String {
        if (!product.imageUris.isNullOrEmpty()) {
            return "https://pic.hse24-dach.net/media/de/products/" + product.imageUris[0] + "pics480.jpg"
        }
        return "https://waterfountain.no/wp-content/uploads/2019/11/placeholder.png"
    }

    fun displayPrice(): String {
        return product.productPrice.price.toString() + "€"
    }

    fun displayQuantity(): String {
        return "x$quantity"
    }
}