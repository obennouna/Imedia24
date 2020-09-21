package com.obennouna.imedia24.viewmodel.productDetail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obennouna.imedia24.pojo.ProductDetail
import com.obennouna.imedia24.repository.productDetail.ProductDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductDetailViewModel: ViewModel() {

    private val productDetailRepository = ProductDetailRepository()
    private val _productDetail: MutableLiveData<ProductDetail> = MutableLiveData()

    var productDetail: LiveData<ProductDetail> = _productDetail

    fun getProductsByCategory(context: Context, sku: Int) {
        _productDetail.apply {
            GlobalScope.launch(Dispatchers.Main) {
                value = context.let { productDetailRepository.getProductDetailBySKU(it, sku) }
            }
        }
    }

    fun buildImageUrl(): String {
        if (!productDetail.value!!.imageUris.isNullOrEmpty()) {
            return "https://pic.hse24-dach.net/media/de/products/" + productDetail.value!!.imageUris[0] + "pics480.jpg"
        }
        return "https://waterfountain.no/wp-content/uploads/2019/11/placeholder.png"
    }
}