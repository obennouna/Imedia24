package com.obennouna.imedia24.ui.productDetail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.obennouna.imedia24.R
import com.obennouna.imedia24.pojo.Product
import com.obennouna.imedia24.pojo.ProductDetail
import com.obennouna.imedia24.repository.cart.CartRepository
import com.obennouna.imedia24.viewmodel.productDetail.ProductDetailViewModel
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductDetailFragment : Fragment(), View.OnClickListener {

    companion object {
        private const val PRODUCT = "PRODUCT"
        fun navigateTo(product: Product): ProductDetailFragment {
            val fragment = ProductDetailFragment()
            val bundle = Bundle()

            bundle.putParcelable(PRODUCT, product)
            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var productDetailViewModel: ProductDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productDetailViewModel = ViewModelProvider(this).get(ProductDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_product_detail, container, false)
        productDetailViewModel.productDetail.observe(viewLifecycleOwner, Observer {
            displayProducts(it)
            progress_circular.visibility = View.GONE
        })
        return root
    }

    private fun displayProducts(productDetail: ProductDetail) {
        context?.let {
            Glide
                .with(it)
                .load(productDetailViewModel.buildImageUrl())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.baseline_attach_money_black_24)
                        .error(R.drawable.baseline_attach_money_black_24).dontTransform()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(product_image)
        }
        product_title.text = productDetail.title
        product_rating.rating = productDetail.averageStars
        product_reference_price.text = productDetailViewModel.displayReferencePrice(context)
        product_price.text = getString(R.string.price, productDetail.productPrice.price)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            product_description.text =
                Html.fromHtml(productDetail.longDescription, Html.FROM_HTML_MODE_COMPACT)
        } else {
            product_description.text = Html.fromHtml(productDetail.longDescription)
        }
        product_add_cart.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (requireArguments().containsKey(PRODUCT)) {
            productDetailViewModel.getProductsByCategory(
                requireContext(),
                requireArguments().getParcelable<Product>(PRODUCT)!!.sku
            )
        }
    }

    override fun onClick(v: View?) {
        GlobalScope.launch(Dispatchers.Main) {
            context?.let { CartRepository().insertProduct(it, requireArguments().getParcelable(PRODUCT)!!) }
        }
        Snackbar.make(
            product_title,
            productDetailViewModel.productDetail.value!!.nameShort+ " added to Cart",
            Snackbar.LENGTH_LONG
        ).show()
    }
}