package com.obennouna.imedia24.ui.productDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.obennouna.imedia24.R
import com.obennouna.imedia24.pojo.Product

class ProductDetailActivity : AppCompatActivity() {

    companion object {
        private const val PRODUCT = "PRODUCT"
        fun navigateTo(product: Product, context: Context): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT, product)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        if (savedInstanceState == null && intent.hasExtra(PRODUCT)) {
            val product = intent.getParcelableExtra<Product>(PRODUCT)
            supportFragmentManager.beginTransaction()
                .replace(R.id.root_container, ProductDetailFragment.navigateTo(product!!))
                .commit()
            title = product.nameShort
        }
    }
}