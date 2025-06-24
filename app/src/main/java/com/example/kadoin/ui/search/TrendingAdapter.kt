package com.example.kadoin.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R
import com.example.kadoin.ui.home.GiftItem

class TrendingAdapter(
    private val items: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    inner class TrendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_trending_image)
        private val tvName: TextView = itemView.findViewById(R.id.tv_trending_name)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_trending_price)

        fun bind(item: GiftItem) {
            tvName.text = item.name
            tvPrice.text = item.price
            
            // Set image based on item types
            val imageRes = when (item.image) {
                "trending_1" -> R.drawable.tws
                "trending_2" -> R.drawable.smartwatch
                "trending_3" -> R.drawable.speaker
                else -> R.drawable.sample_gift_box
            }
            ivImage.setImageResource(imageRes)
            
            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trending, parent, false)
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
