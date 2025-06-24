package com.example.kadoin.ui.favorite

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R
import com.example.kadoin.ui.home.GiftItem

class FavoriteAdapter(
    private val items: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit,
    private val onFavoriteClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivProductImage: ImageView = itemView.findViewById(R.id.iv_product_image)
        private val tvProductName: TextView = itemView.findViewById(R.id.tv_product_name)
        private val tvRating: TextView = itemView.findViewById(R.id.tv_rating)
        private val tvReviews: TextView = itemView.findViewById(R.id.tv_reviews)
        private val tvCurrentPrice: TextView = itemView.findViewById(R.id.tv_current_price)
        private val tvOriginalPrice: TextView = itemView.findViewById(R.id.tv_original_price)
        private val tvCategory: TextView = itemView.findViewById(R.id.tv_category)
        private val btnFavorite: ImageButton = itemView.findViewById(R.id.btn_favorite)

        fun bind(item: GiftItem) {
            tvProductName.text = item.name
            tvRating.text = item.rating.toString()
            tvReviews.text = item.reviews
            tvCurrentPrice.text = item.price

            // Set original price with strikethrough if available
            if (item.originalPrice.isNotEmpty()) {
                tvOriginalPrice.text = item.originalPrice
                tvOriginalPrice.paintFlags = tvOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                tvOriginalPrice.visibility = View.VISIBLE
            } else {
                tvOriginalPrice.visibility = View.GONE
            }

            // Set category label
            tvCategory.text = when (item.image) {
                "fav_1" -> "Best Seller"
                "fav_2" -> "Stok Terbatas"
                "fav_3" -> "Premium"
                "fav_4" -> "Terlaris"
                "fav_5" -> "Limited Edition"
                "fav_6" -> "Stok Terbatas"
                else -> "Best Seller"
            }

            // Set category color
            val categoryColor = when (tvCategory.text) {
                "Best Seller" -> R.color.red_primary
                "Stok Terbatas" -> R.color.teal_700
                "Premium" -> R.color.purple_500
                "Terlaris" -> R.color.red_primary
                "Limited Edition" -> R.color.purple_700
                else -> R.color.red_primary
            }
            tvCategory.setTextColor(itemView.context.getColor(categoryColor))

            // Set product image
            val imageRes = when (item.image) {
                "fav_1" -> R.drawable.kotak_kado
                "fav_2" -> R.drawable.lilin
                "fav_3" -> R.drawable.jam_tangan
                "fav_4" -> R.drawable.kotak_coklat
                "fav_5" -> R.drawable.hampers
                "fav_6" -> R.drawable.parfum_pria
                else -> R.drawable.sample_gift_box
            }
            ivProductImage.setImageResource(imageRes)

            // Handle clicks
            btnFavorite.setOnClickListener {
                onFavoriteClick(item)
            }

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
