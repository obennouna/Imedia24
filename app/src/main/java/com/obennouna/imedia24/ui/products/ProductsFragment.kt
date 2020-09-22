package com.obennouna.imedia24.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.obennouna.imedia24.R
import com.obennouna.imedia24.pojo.Category
import com.obennouna.imedia24.pojo.Product
import com.obennouna.imedia24.repository.cart.CartRepository
import com.obennouna.imedia24.ui.productDetail.ProductDetailActivity
import com.obennouna.imedia24.viewmodel.product.ProductViewModel
import com.obennouna.imedia24.viewmodel.product.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductsFragment: Fragment(), ProductsAdapter.OnItemClickListener {

    companion object {
        private const val CATEGORY = "CATEGORY"
        fun navigateTo(category: Category): ProductsFragment {
            val fragment = ProductsFragment()
            val bundle = Bundle()

            bundle.putParcelable(CATEGORY, category)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var productViewModel: ProductsViewModel
    private val productsAdapter = ProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productViewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_products, container, false)
        productViewModel.products.observe(viewLifecycleOwner, Observer {
            displayProducts(it)
            progress_circular.visibility = View.GONE
        })
        return root
    }

    private fun displayProducts(products: List<ProductViewModel>) {
        productsAdapter.setData(products)
        productsAdapter.notifyDataSetChanged()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        products_rv.setHasFixedSize(true)
        products_rv.layoutManager = LinearLayoutManager(context)
        products_rv.itemAnimator = DefaultItemAnimator()
        products_rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        products_rv.adapter = productsAdapter
        productsAdapter.onItemClickListener(this)

        if (requireArguments().containsKey(CATEGORY)) {
            productViewModel.getProductsByCategory(
                requireContext(),
                requireArguments().getParcelable<Category>(CATEGORY)!!.categoryId
            )
        }
    }

    override fun onItemClickListener(product: ProductViewModel) {
        startActivity(context?.let { ProductDetailActivity.navigateTo(product.product, it) })
    }

    override fun onItemAddedToCart(product: Product) {
        GlobalScope.launch(Dispatchers.Main) {
            context?.let { CartRepository().insertProduct(it, product) }
        }
        Snackbar.make(
            products_rv,
            product.nameShort+ " added to Cart",
            Snackbar.LENGTH_LONG
        ).show()
    }
}