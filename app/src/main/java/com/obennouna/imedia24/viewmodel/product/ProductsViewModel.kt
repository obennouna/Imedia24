package com.obennouna.imedia24.viewmodel.product

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obennouna.imedia24.pojo.Product
import com.obennouna.imedia24.repository.product.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val productRepository = ProductsRepository()
    private val _products: MutableLiveData<List<ProductViewModel>> = MutableLiveData()

    var products: LiveData<List<ProductViewModel>> = _products

    fun getProductsByCategory(context: Context, categoryId: Int) {
        _products.apply {
            GlobalScope.launch(Dispatchers.Main) {
                val listProducts = context.let { productRepository.getProductsByCategory(it, categoryId) }
                value = transformToViewModel(listProducts)
            }
        }
    }

    private fun transformToViewModel(listProducts: List<Product>): List<ProductViewModel>? {
        val toReturn: ArrayList<ProductViewModel> = ArrayList()
        for (product in listProducts) {
            toReturn.add(
                ProductViewModel(
                    product
                )
            )
        }
        return toReturn
    }
}