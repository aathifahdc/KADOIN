package com.example.kadoin.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R
import com.example.kadoin.ui.home.GiftItem

class ParfumAdapter(
    private val items: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<ParfumAdapter.ParfumViewHolder>() {

    inner class ParfumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_parfum_image)
        private val tvName: TextView = itemView.findViewById(R.id.tv_parfum_name)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_parfum_price)

        fun bind(item: GiftItem) {
            tvName.text = item.name
            tvPrice.text = item.price

            // Set image based on item type
            val imageRes = when (item.image) {
                "parfum_1" -> R.drawable.parfum_pria
                "parfum_2" -> R.drawable.parfum_mawar
                else -> R.drawable.sample_gift_box
            }
            ivImage.setImageResource(imageRes)

            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParfumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_parfum, parent, false)
        return ParfumViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParfumViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
