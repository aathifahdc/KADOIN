package com.example.kadoin.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R

class InspirasiHomeAdapter(
    private val items: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<InspirasiHomeAdapter.InspirasiViewHolder>() {

    inner class InspirasiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_inspirasi_image)
        private val tvName: TextView = itemView.findViewById(R.id.tv_inspirasi_name)
        private val tvRating: TextView = itemView.findViewById(R.id.tv_inspirasi_rating)
        private val tvReviews: TextView = itemView.findViewById(R.id.tv_inspirasi_reviews)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_inspirasi_price)
        private val btnFavorite: ImageButton = itemView.findViewById(R.id.btn_inspirasi_favorite)

        fun bind(item: GiftItem) {
            tvName.text = item.name
            tvPrice.text = item.price
            tvRating.text = item.rating.toString()
            tvReviews.text = item.reviews

            // Set image based on item type
            val imageRes = when (item.image) {
                "gift_box_luxury" -> R.drawable.kotak_kado
                "watch_classic" -> R.drawable.jam_tangan
                "parfum_signature" -> R.drawable.parfum_mawar
                "wallet_leather" -> R.drawable.dompet
                "inspirasi_1" -> R.drawable.kotak_kado
                "inspirasi_2" -> R.drawable.jam_tangan
                "inspirasi_3" -> R.drawable.parfum_mawar
                "inspirasi_4" -> R.drawable.dompet
                else -> R.drawable.sample_gift_box
            }
            ivImage.setImageResource(imageRes)

            btnFavorite.setOnClickListener {
                // Handle favorite toggle
            }

            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspirasiViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inspirasi_home, parent, false)
        return InspirasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: InspirasiViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
