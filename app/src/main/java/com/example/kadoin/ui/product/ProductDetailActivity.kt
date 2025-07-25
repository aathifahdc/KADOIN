package com.example.kadoin.ui.product

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kadoin.databinding.ActivityProductDetailBinding
import com.example.kadoin.ui.home.GiftItem
import com.example.kadoin.ui.home.GiftAdapter
import com.example.kadoin.R

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    companion object {
        const val EXTRA_PRODUCT_NAME = "extra_product_name"
        const val EXTRA_PRODUCT_PRICE = "extra_product_price"
        const val EXTRA_PRODUCT_IMAGE = "extra_product_image"
        const val EXTRA_PRODUCT_RATING = "extra_product_rating"
        const val EXTRA_PRODUCT_REVIEWS = "extra_product_reviews"
        const val EXTRA_PRODUCT_ORIGINAL_PRICE = "extra_product_original_price"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupProductDetails()
        setupRecommendations()
        setupClickListeners()
    }

    private fun setupProductDetails() {
        val productName = intent.getStringExtra(EXTRA_PRODUCT_NAME) ?: "Kotak Kado Premium dengan Pita Elegan"
        val productPrice = intent.getStringExtra(EXTRA_PRODUCT_PRICE) ?: "Rp 189.000"
        val productImage = intent.getStringExtra(EXTRA_PRODUCT_IMAGE) ?: "gift_1"
        val productRating = intent.getFloatExtra(EXTRA_PRODUCT_RATING, 4.8f)
        val productReviews = intent.getStringExtra(EXTRA_PRODUCT_REVIEWS) ?: "(125)"
        val originalPrice = intent.getStringExtra(EXTRA_PRODUCT_ORIGINAL_PRICE) ?: ""

        binding.tvProductName.text = productName
        binding.tvProductPrice.text = productPrice
        binding.tvProductRating.text = productRating.toString()
        binding.tvProductReviews.text = productReviews

        if (originalPrice.isNotEmpty()) {
            binding.tvOriginalPrice.text = originalPrice
            binding.tvOriginalPrice.visibility = android.view.View.VISIBLE
            binding.tvOriginalPrice.paintFlags = binding.tvOriginalPrice.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            binding.tvOriginalPrice.visibility = android.view.View.GONE
        }

        val imageRes = when (productImage) {
            "smartphone_premium" -> R.drawable.iphone_promax
            "headphone_wireless" -> R.drawable.headphone
            "smartwatch_premium" -> R.drawable.smartwatch
            "gift_box_luxury" -> R.drawable.kotak_kado
            "watch_classic" -> R.drawable.jam_tangan
            "parfum_signature" -> R.drawable.parfum_mawar
            "wallet_leather" -> R.drawable.dompet
            "earbuds_premium" -> R.drawable.tws
            "gift_1" -> R.drawable.smarthome
            "gift_2" -> R.drawable.fitness
            "gift_3" -> R.drawable.powerbank
            else -> R.drawable.sample_gift_box
        }
        binding.ivProductImage.setImageResource(imageRes)

        binding.tvProductDescription.text = "Kotak kado premium dengan desain elegan dan pita berkualitas tinggi. Sempurna untuk berbagai momen spesial seperti ulang tahun, anniversary, dan acara penting lainnya. Dibuat dengan bahan berkualitas tinggi yang tahan lama dan memberikan kesan mewah pada hadiah Anda."
    }

    private fun setupRecommendations() {
        val recommendations = listOf(
            GiftItem("gift_1", "Jam Tangan Mewah Exclusive", "Rp 1.250.000", 4.7f, "(45)"),
            GiftItem("gift_2", "Set Lilin Aromaterapi Premium", "Rp 215.000", 4.9f, "(87)"),
            GiftItem("gift_3", "Parfum Premium Elegant Collection", "Rp 325.000", 4.8f, "(92)")
        )

        val adapter = GiftAdapter(recommendations) { gift ->
            // Handle recommendation click - bisa membuka detail produk baru
        }

        binding.rvRecommendations.apply {
            layoutManager = LinearLayoutManager(this@ProductDetailActivity, LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnFavorite.setOnClickListener {
            val currentIcon = binding.btnFavorite.drawable
            if (currentIcon.constantState == getDrawable(R.drawable.ic_favorite_border)?.constantState) {
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite_24)
            } else {
                binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border)
            }
        }

        // Tombol Kunjungi Toko
        binding.btnVisitStore.setOnClickListener {
            val productImage = intent.getStringExtra(EXTRA_PRODUCT_IMAGE)
            val url = when (productImage) {
                "smartphone_premium" -> "https://id.shp.ee/ANwLzii"
                // Tambahkan produk lain jika diperlukan:
                // "headphone_wireless" -> "https://..."
                else -> null
            }

            if (url != null) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            } else {
                // Optional: tampilkan pesan jika link belum tersedia
                // Toast.makeText(this, "Link belum tersedia untuk produk ini", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnDescription.setOnClickListener {
            binding.scrollView.smoothScrollTo(0, binding.tvDescriptionTitle.top)
        }
    }
}
