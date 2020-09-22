package com.obennouna.imedia24.ui.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.obennouna.imedia24.R

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        supportFragmentManager.beginTransaction()
            .replace(R.id.root_container, CartFragment())
            .commit()
    }
}