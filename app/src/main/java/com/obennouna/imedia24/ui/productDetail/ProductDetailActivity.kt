package com.obennouna.imedia24.ui.productDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.obennouna.imedia24.R
import com.obennouna.imedia24.pojo.Product
import com.obennouna.imedia24.ui.cart.CartActivity

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.items_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.display_cart) {
            startActivity(Intent(this, CartActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}