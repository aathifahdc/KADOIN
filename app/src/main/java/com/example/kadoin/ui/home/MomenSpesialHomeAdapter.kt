package com.example.kadoin.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kadoin.R

class MomenSpesialHomeAdapter(
    private val items: List<GiftItem>,
    private val onItemClick: (GiftItem) -> Unit
) : RecyclerView.Adapter<MomenSpesialHomeAdapter.MomenSpesialViewHolder>() {

    inner class MomenSpesialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_product_image)
        private val tvDiscount: TextView = itemView.findViewById(R.id.tv_discount)
        private val tvName: TextView = itemView.findViewById(R.id.tv_product_name)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_product_price)
        private val tvOriginalPrice: TextView = itemView.findViewById(R.id.tv_original_price)

        fun bind(item: GiftItem) {
            tvName.text = item.name
            tvPrice.text = item.price
            tvDiscount.text = item.discount
            tvOriginalPrice.text = item.originalPrice

            // Add strikethrough effect programmatically
            tvOriginalPrice.paintFlags = tvOriginalPrice.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG

            // Set image based on item type
            val imageRes = when (item.image) {
                "smartphone_premium" -> R.drawable.iphone_promax
                "headphone_wireless" -> R.drawable.headphone
                "smartwatch_premium" -> R.drawable.smartwatch
                "momen_1" -> R.drawable.iphone_promax
                "momen_2" -> R.drawable.headphone
                "momen_3" -> R.drawable.smartwatch
                else -> R.drawable.sample_gift_box
            }
            ivImage.setImageResource(imageRes)

            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomenSpesialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_momen_spesial_home, parent, false)
        return MomenSpesialViewHolder(view)
    }

    override fun onBindViewHolder(holder: MomenSpesialViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
