package com.example.kadoin.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R
import com.example.kadoin.ui.home.GiftItem

class RecommendationAdapter(
    private val gifts: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder>() {

    inner class RecommendationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivGiftImage: ImageView = itemView.findViewById(R.id.iv_gift_image)
        private val tvGiftName: TextView = itemView.findViewById(R.id.tv_gift_name)
        private val tvGiftPrice: TextView = itemView.findViewById(R.id.tv_gift_price)
        private val tvRating: TextView = itemView.findViewById(R.id.tv_rating)
        private val tvSold: TextView = itemView.findViewById(R.id.tv_sold)
        private val btnFavorite: ImageButton = itemView.findViewById(R.id.btn_favorite)

        fun bind(gift: GiftItem) {
            tvGiftName.text = gift.name
            tvGiftPrice.text = gift.price
            tvRating.text = gift.rating.toString()
            tvSold.text = gift.sold

            // Set placeholder image
            val imageRes = when (gift.image) {
                "gift_1" -> R.drawable.kotak_kado
                "gift_2" -> R.drawable.jam_tangan
                "gift_3" -> R.drawable.lilin
                "gift_4" -> R.drawable.kotak_coklat
                "gift_5" -> R.drawable.album_photo
                "gift_6" -> R.drawable.sk2
                "gift_7" -> R.drawable.anggur
                "gift_8" -> R.drawable.kalung
                else -> R.drawable.sample_gift_box
            }
            ivGiftImage.setImageResource(imageRes)

            btnFavorite.setOnClickListener {
                // Handle favorite toggle
            }

            itemView.setOnClickListener {
                onItemClick(gift)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommendation, parent, false)
        return RecommendationViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(gifts[position])
    }

    override fun getItemCount() = gifts.size
}
