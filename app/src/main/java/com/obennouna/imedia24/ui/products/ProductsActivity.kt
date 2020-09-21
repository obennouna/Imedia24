package com.obennouna.imedia24.ui.products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.obennouna.imedia24.R
import com.obennouna.imedia24.pojo.Category

class ProductsActivity : AppCompatActivity() {

    companion object {
        private const val CATEGORY = "CATEGORY"
        fun navigateTo(category: Category, context: Context): Intent {
            val intent = Intent(context, ProductsActivity::class.java)
            intent.putExtra(CATEGORY, category)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        if (savedInstanceState == null && intent.hasExtra(CATEGORY)) {
            val category = intent.getParcelableExtra<Category>(CATEGORY)
            supportFragmentManager.beginTransaction()
                .replace(R.id.root_container, ProductsFragment.navigateTo(category!!))
                .commit()
            title = category.displayName
        }
    }
}